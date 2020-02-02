package org.opendatacore.kernel

import org.jboss.logging.Logger
import org.opendatacore.kernel.metacon.data.util.app.AppData
import org.opendatacore.kernel.metacon.plugin.util.security.SecurityUtils

// Create a logger instance for the AppData class
private val _logger = Logger.getLogger(AppData::class.java)

fun main(args: Array<String>) {
    // Print a message to the console
    println("Hello ODC World!")

    // Create an instance of AppData using the builder pattern and set some properties
    var app = AppData()
        .name("ODC Kernel Library")
        .version("1.0.0")
        .description("ODC Kernel Library is a core library that provides essential functionalities for all ODC modules. " +
                "It includes various components, such as Grid computing, meta connector, common data interchange model, dynamic coroutines, security, and file persistence. " +
                "This library serves as the foundation for building ODC modules and ensures seamless integration between them.")
        .build()

    // Convert the AppData instance to JSON format and log it using the JBoss logger
    _logger.info("Kernel Data : ${"\n\n" + app.toJson()}")

    // Generate random keys using the SecurityUtils class
    println("Random key: ${SecurityUtils.generateRandomKey()}")

    // Print the program arguments provided when running the application
    println("Program arguments: ${args.joinToString()}")

    // Print the Kernel Data in JSON format again
    println("Kernel Data : ${"\n\n" + app.toJson()}")
}
