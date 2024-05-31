#!/bin/bash
#doar am vrut sa dau run la aplicatie mai usor da nu merge
# Navigate to the directory containing your Java files
cd ~/Desktop/java-proiect/smart-city/demo/src/main/java/com/example

# Create output directory if it doesn't exist
mkdir -p out/production/classes

# Compile the server and client Java files
javac -d out/production/classes src/com/example/server/Server.java src/com/example/server/ClientHandler.java
javac -d out/production/classes --module-path /path/to/javafx-sdk-11/lib --add-modules javafx.controls,javafx.fxml src/com/example/client/App.java src/com/example/client/Client.java

# Run the server in the background
java -cp out/production/classes com.example.server.Server &

# Give the server a moment to start
sleep 2

# Run the client application
java --module-path /path/to/javafx-sdk-11/lib --add-modules javafx.controls,javafx.fxml -cp out/production/classes com.example.client.App
