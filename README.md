Client-Server Messenger
=======================

A client-server implementation of a messenger.

The platform is Linux (aviary machines).
Type the commands in this order in the command line:

1. Compile java source files

```
javac msgServer.java
javac msgClient.java
```

2. Run the server first on owl.cs.umanitoba.ca, then run the
client on another machine

```
java msgServer
java msgClient
```

3. The client can indicate that it is done sending messages
by typing "logout" (without quotations)
