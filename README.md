# Data Center Management API

A Java-based API that simulates the management and monitoring of a data center infrastructure.

The project focuses on object-oriented design, modular architecture, design patterns, dynamic resource management, and custom exception handling.

## Project Overview

The application simulates a data center environment containing servers, resource groups, and system-wide notifications.

It provides functionality for dynamically managing monitored servers and organizing them into resource groups while maintaining a modular and maintainable codebase.

The project was developed with a strong focus on Object-Oriented Programming principles and software design patterns.

## Main Features

- Dynamic management of monitored servers
- Resource group management
- System-wide notifications
- Server and resource state management
- Input validation
- Custom exception handling
- Modular and decoupled architecture
- Object-oriented design using Java

## Object-Oriented Programming

The project applies several core OOP concepts:

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Composition
- Interfaces
- Separation of responsibilities

These concepts are used to model the different entities and interactions within the simulated data center.

## Design Patterns

Several design patterns were used to improve the architecture and maintainability of the application.

### Singleton

Used where a single shared instance is required for managing centralized application functionality.

### Builder

Used to construct complex objects in a controlled and readable way.

### Factory

Used to encapsulate object creation and reduce direct dependencies between components.

### Observer

Used to implement the notification mechanism, allowing interested components to react to changes in the system.

## Exception Handling

The project includes a custom exception-handling framework used to validate input and maintain operational integrity.

Invalid operations and inconsistent states are handled through dedicated exceptions rather than being silently ignored.

This approach helps make the application more robust and easier to debug.

## Architecture

The application is organized around independent components representing the main entities and operations of the simulated data center.

The design aims to minimize coupling between components and keep responsibilities clearly separated.

This makes the system easier to extend and maintain as new functionality is added.

## Technologies

- Java
- Object-Oriented Programming
- Design Patterns
- Custom Exception Handling
- Data Structures
- Modular Software Design
