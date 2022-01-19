// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "../Wrapper.sol";

contract Dummy1 {
    address public wrapperAddress;

    constructor(address _wrapperAddress) {
        wrapperAddress = _wrapperAddress;
    }

    function wrap(uint256 amount) public {
        wrapperAddress.call{value: amount}("");
    }

    function unwrap(uint256 amount) public {
        Wrapper(payable(wrapperAddress)).transfer(wrapperAddress, amount);
    }

    function sequence(uint256 amount) external {
        wrap(amount);
        unwrap(amount);
        wrap(amount);
        unwrap(amount);
        wrap(amount);
        unwrap(amount);
        wrap(amount);
        unwrap(amount);
    }

    fallback() external payable {}
}
