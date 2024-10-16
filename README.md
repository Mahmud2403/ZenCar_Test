## Project Overview

This project follows the **Model-View-Intent (MVI)** architectural pattern and is built using **Jetpack Compose** for UI. The project involves a user registration, login (authorization), and a main screen that manages user information and settings.

### Key Libraries Used:
- **Datastore**: For data storage and management.
- **Jetpack Compose**: To build declarative UI components.
- **Jetpack Navigation**: To handle navigation between screens.
- **Hilt**: For dependency injection.
- **Room**: For local database management.
- **Coil**: For loading images, such as avatars.

## Features

### 1. Registration Screen
- **Functionality**:
  - Avatar selection.
  - Name input.
  - Date of birth selection using a date picker.
  - Password and confirm password fields with validation.
  - Adds the user to the database upon successful validation.

### 2. Authorization Screen
- **Functionality**:
  - Input name and password with validation.
  - Checks if the user exists and if the password matches.
  - Redirects to the main screen upon successful authorization.

### 3. Main Screen
- **Functionality**:
  - Displays authorized users.
  - Allows deletion of users authorized after the current user.
  - Deletion is performed with a right swipe gesture animation.
  - Includes the ability to change the theme color palette.

## How to Build and Run
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Sync Gradle to install dependencies.
4. Build and run the project on an Android device or emulator.

## Architecture
The project uses the **MVI** pattern, which ensures a unidirectional data flow:
- **Model**: Represents the state of the UI.
- **View**: Displays the UI state to the user.
- **Intent**: Handles the user's actions and updates the state.

### Screens and Flows:
1. **Registration Flow**: Handles user registration including avatar, name, birthdate, and password input with validation.
2. **Login Flow**: Validates user credentials against stored data.
3. **Main Flow**: Displays users, allows deletion of certain users, and manages the app theme.

## Libraries and Tools
- **Jetpack Compose**: UI framework for declarative Android apps.
- **Jetpack Navigation**: Navigation between screens.
- **Room**: Persistent local storage using SQLite.
- **Hilt**: Dependency injection framework.
- **Coil**: Image loading library for avatars.
- **Datastore**: Used for lightweight data persistence.

## Preview:
<img src="https://github.com/user-attachments/assets/d064de5a-9a48-4c9a-9a2c-7b87d72991b0" height="200">
<img src="https://github.com/user-attachments/assets/d42dccd2-1899-42cb-8c36-13ab2ba92073" height="200">
<img src="https://github.com/user-attachments/assets/e7a824d7-9ba0-41b6-9af2-411f1c6a02a9" height="200">
<img src="https://github.com/user-attachments/assets/f13cf620-379f-4ae0-bf95-9776030f5bb7" height="200">
<img src="https://github.com/user-attachments/assets/c2f5cf10-7a75-4a31-80b3-4d8ad6b141c7" height="200">
<img src="https://github.com/user-attachments/assets/6d0d2def-9713-44ed-b678-abd3cda4d65b" height="200">
<img src="https://github.com/user-attachments/assets/fd17133b-f98f-4923-95cc-cb1dbe6ba0da" height="200">
<img src="https://github.com/user-attachments/assets/8d3e485f-cf4e-4c4a-b243-22e86fbca687" height="200">













