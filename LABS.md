# LAB-1
We will be implementing the tests for the 'Player'.

Start by creating a new feature file.

Set up a background with a fabricator that has some components.

#LAB-2 & 3
As described in the README.md, a player can perform two types of actions. Write Scenarios for them.

Start with the happy path and then add cases where exceptions should be thrown. Your 'When'-steps should call a method on the PlayerService.

#Final LAB

Go over your test code. Did you make optimal use of Cucumber's features like:
- Outline
- Parameter types
- DataTable types

If not, try to refactor for better reusability

Also, I lied. There is at least 1 bug and 1 exploit in this code. Did your tests find them?

Hint: the bug is in the Player.addOutpostRoom and the exploit is in Player.gatherResources