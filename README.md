# Football World Cup Score Board 

This project is the implementation of the Live Football World Cup Score Board as a simple
library.

# Features:
- **Start a Match:**
    Begins a new match with an Initial score of 0-0;
- **Update score:**
    Updates the score of an ongoing match;
- **Finish Match:**
    Finishes a match and removes it from the scoreboard.
- **Get a summary:**
    Retrieves current summary of all ongoing matches

# Assumptions and design decisions
- A team can participate in one match only at the same time, assuming we wouldn't have some second division teams playing at the same time (I don't know football);
- In-memory data structures (List and Set) are used for the sake of simplicity, considering the small size of the application;
