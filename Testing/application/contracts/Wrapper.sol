// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract Wrapper is ERC20("Wrapper", "WRPR") {
    function _afterTokenTransfer(
        address from,
        address to,
        uint256 amount
    ) internal override {
        if (to == address(this)) {
            _burn(address(this), amount);
            (bool succ, ) = msg.sender.call{value: amount}("");
            require(succ, "Wrapper: unwrapping failed");
        }
    }

    fallback() external payable {
        _mint(msg.sender, msg.value);
    }
}
