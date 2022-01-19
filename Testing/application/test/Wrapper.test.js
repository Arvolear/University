const { toBN, wei, accounts } = require("../scripts/helpers/utils");
const truffleAssert = require("truffle-assertions");
const { assert } = require("chai");

const Wrapper = artifacts.require("Wrapper");
const Attacker = artifacts.require("Attacker");
const Dummy1 = artifacts.require("Dummy1");
const Dummy2 = artifacts.require("Dummy2");

Wrapper.numberFormat = "BigNumber";
Attacker.numberFormat = "BigNumber";
Dummy1.numberFormat = "BigNumber";
Dummy2.numberFormat = "BigNumber";

describe("Wrapper", async () => {
  let OWNER;
  let SECOND;

  let wrapper;

  before("setup", async () => {
    OWNER = await accounts(0);
    SECOND = await accounts(1);
  });

  beforeEach("setup", async () => {
    wrapper = await Wrapper.new();
  });

  it("should receive WETH", async () => {
    let beforeWrapperBalance = toBN(await web3.eth.getBalance(wrapper.address));
    let beforeOwnerBalance = toBN(await web3.eth.getBalance(OWNER));

    assert.equal(beforeWrapperBalance.toFixed(), "0");
    assert.closeTo(beforeOwnerBalance.toNumber(), toBN(wei("10000")).toNumber(), toBN(wei("0.1")).toNumber());

    await wrapper.send(wei("1"));

    let afterWrapperBalance = toBN(await web3.eth.getBalance(wrapper.address));
    let afterOwnerBalance = toBN(await web3.eth.getBalance(OWNER));

    assert.equal(afterWrapperBalance.toFixed(), wei("1"));
    assert.closeTo(afterOwnerBalance.toNumber(), toBN(wei("9999")).toNumber(), toBN(wei("0.1")).toNumber());

    assert.equal((await wrapper.totalSupply()).toFixed(), wei("1"));
    assert.equal((await wrapper.balanceOf(OWNER)).toFixed(), wei("1"));
  });

  it("should receive ETH", async () => {
    let beforeWrapperBalance = toBN(await web3.eth.getBalance(wrapper.address));
    let beforeOwnerBalance = toBN(await web3.eth.getBalance(OWNER));

    await wrapper.send(wei("1"));
    await wrapper.transfer(wrapper.address, wei("1"));

    assert.equal(beforeWrapperBalance.toFixed(), "0");
    assert.equal(toBN(await web3.eth.getBalance(wrapper.address)).toFixed(), beforeWrapperBalance.toFixed());

    assert.closeTo(
      toBN(await web3.eth.getBalance(OWNER)).toNumber(),
      beforeOwnerBalance.toNumber(),
      toBN(wei("0.001")).toNumber()
    );
  });

  it("should burn WETH", async () => {
    await wrapper.send(wei("1"));
    await wrapper.transfer(wrapper.address, wei("1"));

    assert.equal((await wrapper.totalSupply()).toFixed(), "0");
  });

  it("should transfer WETH to a different account", async () => {
    await wrapper.send(wei("1"));

    assert.equal((await wrapper.totalSupply()).toFixed(), wei("1"));
    assert.equal((await wrapper.balanceOf(OWNER)).toFixed(), wei("1"));
    assert.equal((await wrapper.balanceOf(SECOND)).toFixed(), "0");

    await wrapper.transfer(SECOND, wei("1"));

    assert.equal((await wrapper.totalSupply()).toFixed(), wei("1"));
    assert.equal((await wrapper.balanceOf(OWNER)).toFixed(), "0");
    assert.equal((await wrapper.balanceOf(SECOND)).toFixed(), wei("1"));
  });

  it("should give ETH to a correct account", async () => {
    await wrapper.send(wei("1"));

    assert.closeTo(
      toBN(await web3.eth.getBalance(OWNER)).toNumber(),
      toBN(wei("9997")).toNumber(),
      toBN(wei("0.1")).toNumber()
    );
    assert.closeTo(
      toBN(await web3.eth.getBalance(SECOND)).toNumber(),
      toBN(wei("10000")).toNumber(),
      toBN(wei("0.1")).toNumber()
    );

    assert.equal((await wrapper.balanceOf(OWNER)).toFixed(), wei("1"));
    assert.equal((await wrapper.balanceOf(SECOND)).toFixed(), "0");

    await wrapper.approve(SECOND, wei("1"));
    await wrapper.transferFrom(OWNER, wrapper.address, wei("1"), { from: SECOND });

    assert.equal((await wrapper.balanceOf(OWNER)).toFixed(), "0");
    assert.equal((await wrapper.balanceOf(SECOND)).toFixed(), "0");

    assert.closeTo(
      toBN(await web3.eth.getBalance(OWNER)).toNumber(),
      toBN(wei("9997")).toNumber(),
      toBN(wei("0.1")).toNumber()
    );
    assert.closeTo(
      toBN(await web3.eth.getBalance(SECOND)).toNumber(),
      toBN(wei("10001")).toNumber(),
      toBN(wei("0.1")).toNumber()
    );
  });

  it("should wrap tokens via the contract", async () => {
    const dummy1 = await Dummy1.new(wrapper.address);

    await dummy1.send(wei("1"));

    assert.equal(toBN(await web3.eth.getBalance(dummy1.address)).toFixed(), wei("1"));

    await dummy1.wrap(wei("1"));

    assert.equal(toBN(await web3.eth.getBalance(dummy1.address)).toFixed(), "0");

    assert.equal((await wrapper.totalSupply()).toFixed(), wei("1"));
    assert.equal((await wrapper.balanceOf(dummy1.address)).toFixed(), wei("1"));
  });

  it("should unwrap tokens via the contract's fallback", async () => {
    const dummy1 = await Dummy1.new(wrapper.address);

    await dummy1.send(wei("1"));

    assert.equal(toBN(await web3.eth.getBalance(dummy1.address)).toFixed(), wei("1"));

    await dummy1.wrap(wei("1"));
    await dummy1.unwrap(wei("1"));

    assert.equal(toBN(await web3.eth.getBalance(dummy1.address)).toFixed(), wei("1"));

    assert.equal((await wrapper.totalSupply()).toFixed(), "0");
    assert.equal((await wrapper.balanceOf(dummy1.address)).toFixed(), "0");
  });

  it("should unwrap tokens via the contract's receive", async () => {
    const dummy2 = await Dummy2.new(wrapper.address);

    await wrapper.send(wei("1"));

    await wrapper.transfer(dummy2.address, wei("1"));

    truffleAssert.eventEmitted(await dummy2.unwrap(wei("1")), "Received");

    assert.equal(toBN(await web3.eth.getBalance(dummy2.address)).toFixed(), wei("1"));

    assert.equal((await wrapper.totalSupply()).toFixed(), "0");
    assert.equal((await wrapper.balanceOf(dummy2.address)).toFixed(), "0");
  });

  it("should maintain the token balances", async () => {
    const dummy1 = await Dummy1.new(wrapper.address);

    await dummy1.send(wei("1"));

    await dummy1.sequence(wei("1"));

    assert.equal(toBN(await web3.eth.getBalance(dummy1.address)).toFixed(), wei("1"));

    assert.equal((await wrapper.totalSupply()).toFixed(), "0");
    assert.equal((await wrapper.balanceOf(dummy1.address)).toFixed(), "0");
  });

  it("should not work", async () => {
    const attacker = await Attacker.new(wrapper.address);

    await wrapper.send(wei("10"));

    await wrapper.approve(attacker.address, wei("1"));

    await truffleAssert.reverts(attacker.unwrapFrom(wei("1")), "Wrapper: unwrapping failed");
  });
});
