const Wrapper = artifacts.require("Wrapper");

module.exports = async (deployer) => {
  await deployer.deploy(Wrapper);
};
