# SlotGame

This is web based game. The aim of the project was to improve my competencies on Java, SpringBoot, Angular, WebSocket, Docker.

It has JWT based Auth. NodeJS based Socket.io server for real time chat and multiplaying. 

### Description



Jackpot! You've landed a summer gig in Las Vegas! Unfortunately, its 2021, and the casinos are closed due to COVID-19. Your boss wants to move some of the business online and asks you to build a **spring-boot backend app** — a simple slot machine game, with a little twist. Build it to ensure that the house always wins!

### Slot Game 

- When a player starts a game/session, they are allocated 10 credits.
- Pulling the machine lever (rolling the slots) costs 1 credit.
- The game screen has 1 row with 3 blocks.
- For player to win the roll, they have to get the same symbol in each block — e.g. 3 cherries or 3 oranges.
- There are 4 possible symbols: cherry (10 credits reward), lemon (20 credits reward), orange (30 credits reward), and watermelon (40 credits reward).
- The game (session) state has to be kept on a database table.
- If the player keeps winning, they can play forever, but house has something to say about that...
- There is CASH OUT endpoint, but there's a twist there as well.

### Tasks

- Users register with a username and an initial 10 credits
-   When a user has less than 40 credits in the game session, their rolls are truly random (one out of 4 symbols).
-   If a user has between 40 and 60 credits, then the server begins to slightly cheat:
    -   For each winning roll, before communicating back to client, the server does one 30% chance roll which decides if server will re-roll the that round.
    -   If that roll is true, then server re-rolls and communicates the new result back.
-   If user has above 60 credits, the server acts the same, but in this case the chance of re-rolling the round increases to 60%.
    -   If that roll is true, then server re-rolls and communicates the new result back.
-   There is a cash-out endpoint which ends the game and restarts the session.

### Endpoints (can be more)
- POST /register    - takes only a username in the request
- POST /play        - returns the new score and the symbols rolled
- POST /cashout     - resets the user state to initial and increases the field **withdrawn**
- POST /loan        - increases the user's credit as well as another field **loan**
- GET   /           - returns current state (credits, loan, withdrawn)
