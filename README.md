# The Inventory App

## App Requirements and Goals

The Inventory App was designed to help users efficiently track and manage items in their personal or small business inventory. It allows users to add, edit, and delete items while monitoring quantities. The app addresses the need for a simple, intuitive way to maintain inventory without relying on paper records or complex software. Additionally, it includes optional SMS alerts when stock reaches zero to help users stay proactive.

## Screens and Features

Key screens include the Dashboard, where users view their inventory and add items, and Item Management, where they can edit or delete items. The UI features inline addition, colour-coded quantities, and clear labels to keep tasks straightforward. By focusing on simplicity, minimising clicks, and providing immediate feedback, the app ensures users can complete actions quickly and accurately within a user-centered and effective design.

## Coding Approach

I approached development incrementally by building small, testable features and gradually integrating them into the app. Techniques included modular class design, separating UI logic from database handling, and using adapters for dynamic `ListViews`. This strategy promotes maintainability and can be applied in future projects to reduce errors and simplify updates.

## Testing Process

Testing included manually adding, editing, and deleting items, checking SMS alerts, and verifying database updates. Ensuring functionality early and frequently helped catch bugs before they became complex issues. This iterative testing highlighted the importance of handling edge cases, such as zero quantities or empty fields.

## Innovation and Challenges

A small challenge was implementing inline item editing and deletion while keeping the `ListView` updated in real-time. I overcame this by carefully managing adapter notifications and database synchronisation, which ensured that the UI reflected changes immediately.

## Demonstrated Knowledge

I demonstrated proficiency in database integration, `ListView adapters`, and dynamic UI updates, as well as handling permissions like SMS alerts. These components highlight my ability to combine core Android development skills with user-centred design principles effectively.
