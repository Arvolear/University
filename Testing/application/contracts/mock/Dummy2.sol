// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "../Wrapper.sol";

contract Dummy2 {
    event Received(uint256 amount);

    address public wrapperAddress;

    constructor(address _wrapperAddress) {
        wrapperAddress = _wrapperAddress;
    }

    function unwrap(uint256 amount) external {
        Wrapper(payable(wrapperAddress)).transfer(wrapperAddress, amount);
    }

    receive() external payable {
        emit Received(msg.value);
    }
}
