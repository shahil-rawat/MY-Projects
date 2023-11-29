# A4-Journal-App
## Authors
- Deepak Kumar Jha (BITS ID: 2020A7PS0966G, Email: f20200966@goa.bits-pilani.ac.in)
- Shahil Singh Rawat (BITS ID: 2020A7PS0138G, Email: f20200138@goa.bits-pilani.ac.in)

## Description
The Journal App is a simple app that allows users to record and keep track of their daily activities, thoughts, and feelings. Users can create new journal entries at any time, and each entry is stamped with the current date and time. The app also allows users to set a start and end time for each journal entry, which can be useful for tracking how long an activity took or for reflecting on a specific period of time.

### Functionality and Known Issues
The Journal App has the following functionality:
- Users can create and delete new journal entries at any time.
- Each journal entry is stamped with the current date and time.
- Users can set a start and end time for each journal entry.
- The app has a list view that shows all of the user's journal entries.
- The app has a detail view that shows more information about a specific entry, including the entry's title, date, start time, end time, and text.
- Users can share journal entries via SMS, email, or another compatible app.
- Users can view a brief description of the app.
  
### Currently, there are no known issues in the app.

### Tasks Completed
### Implemented Tasks:
- Implemented navigation using nav graph actions.
- Modified database to include new columns.
- Implemented DELETE button to delete journal entries.
- Implemented SHARE button to share journal entries via SMS, email, or other compatible app.
- Added INFO button to lead user to a fragment with a brief description of the app.
- Wrote Espresso test cases to check for accessibility.
When stuck, we had borrowed ideas from vardan, a batchmate of ours.


#### Task  Accessibility
We've addressed accessibility in several ways:
1. **TalkBack Testing**: We ensured the app works well with TalkBack, offering a seamless experience for visually impaired users.
2. **Accessibility Scanner Report**: We resolved accessibility issues, including multiple speakable text problem for better readability and low contrast foreground to background ratio.
3. **Espresso Accessibility Testing**: Integrated Espresso Accessibility for automated checks on various UI elements, maintaining accessibility compliance.

These measures confirm our dedication to making the app accessible to a diverse user base, including those with visual impairments.

### Testing

- We had used monkey tool as well for testing, using the following command:
adb shell monkey -p androidsamples.java.dicegames -v 2000.
- There were no app crashes.
  
Monkey testing involves generating random user interactions to uncover potential issues and improve the app's robustness.

### Time Invested
- Writing Code, Testing, and Solving Accessibility Issues: 35 hours in Total

### Pair-Programming - Scale 1 to 5
- On a scale of 1 to 5, we rate ourselves as **5**.

### Difficulty
- On a scale of 1 to 10, we rate the difficulty of the assignment as **10**.

