So far we have the following problems
- we are duplicating information between the board and the piece. (Violates Dry principal)
- The subscripting is unnatural for the domain.

Now that it has been refactored.
- No duplication of coordinates.
- Subscripting is natural.
- Side effect is that the code is a lot terser.

---
