#Rules of the game

**Note:** While the implementation needed for the labs is present and (hopefully) bug-free, not all rules or concepts mentioned below are present in the code. 

## Setting

We're colonizing a new planet! Before any human set foot on it, we dropped a 'fabricator' on its surface.
Think of it as a giant 3D-printer that produces rooms for the bases we will be building.

Every player of this game is the leader of a group of scientists and tries to build the best base over a number of years (aka turns).
The players will use the fabricator to print new rooms that will be added to their base.
After 10 turns, the player whose base is worth the most points, wins the game.

## Concepts

### Fabricator
_See FabricatorService_

The fabricator randomly generates 6 'OutpostComponents' that can be bought by players. 
An OutpostComponent has a production cost that needs to be paid by the player that wants to add that part to their base.
This cost can be of one or more of the following types: electricity, biomass, water or science and is usually between 0 - 3 of each type.

EG: To build the 'Biolab' component, you need to pay 2 biomass and 2 science resources.

Every room is also worth a number of victory points. The sum of all components in a player's base will result in their final score for a game.

### Player
_See PlayerService_


A player has 3 actions per turn. Once a player has completed all their actions, their turn is over. At the start of their next turn, the actions will refill back to 3.

For every action, a player has 2 options: add a room to their outpost or gather resources.

#### Adding a room
A player selects a room (by id) and adds it their base. This component is then deleted from the memory banks of the fabricator (this means that every component can only be claimed once).
The player has to pay the costs for the component. If they cannot pay the costs, an exception will be thrown.

For the payment, the electricity resource can be used as a stand-in for the other types of resources, but will only be consumed once all units of the non-electricity resource are consumed. 

#### Gathering resources
If a player wants to get their hands on a specific room, but they cannot afford, they can gather resources instead. 
By gathering, you gain exactly 2 resources of your choice. This can be 2 of the same type or 1 resource for two different types.
