# multilevel-hash-treeset
Search Engine Created using multilevel hashing

Using a multi level hash set, any word in the dictionary can be found in O(1) time without collisions. To search for any word it'll take a maximum of 25 hops making the algorithm time efficient

The multi level hashing is done using trees. We embed a tree within every node. On passing from one tree to the next it is actually moving by every letter. This works for the first five letters after which it results in open hashing.
