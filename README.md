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
![image](https://github.com/user-attachments/assets/196f9103-e38b-4a13-8b7c-d3a879d8e259) ![image](https://github.com/user-attachments/assets/598767e2-06fe-4c45-9b38-1a983905c4c4) 
![image](https://github.com/user-attachments/assets/4b6fe794-1e5c-4739-9db9-375def1d20d2) ![image](https://github.com/user-attachments/assets/6a1484aa-7794-4de4-9cf6-04cf8fcab602)
![image](https://github.com/user-attachments/assets/318344df-0c2d-4031-bf15-5e551594630f) ![image](https://github.com/user-attachments/assets/74ad84ef-4fde-427e-bc86-01d9b47e06d4)
![image](https://github.com/user-attachments/assets/f618274d-5ff5-4580-ac3d-d481f2851f33) ![image](https://github.com/user-attachments/assets/7b472199-c5f6-437e-be3e-829647d0242c) 
![image](https://github.com/user-attachments/assets/a358d819-f96a-4ebc-87eb-bcf84ca7b25f) ![image](https://github.com/user-attachments/assets/4086ecc1-4a6b-4627-a0cc-2f8c6ceee21e)
![image](https://github.com/user-attachments/assets/3b0d22c8-7051-432a-bc7f-af86e75aafdb) ![image](https://github.com/user-attachments/assets/d34e1135-aeb5-42fc-bab0-ff158c32547e)











