# PartypushAndroid
Android version of Partypush

**Project Sub-Modules and Libraries:**

- _Assets:_ Custom images.
  
- _LegacyCode:_ Old verson of the app that might contain useful code snippets. Not in development.
  
- _Plan:_ Documents showing the high level structure of the program.
  
- _Project:_ Source code, this is what ships.
  

**INSTALL:**
- Download and install Android Studio or Intellij IDEA 14 (I recommend 14, since that is what I am using)
  - Android Studio: http://developer.android.com/sdk/index.html
  - Intellij 14: https://www.jetbrains.com/idea/whatsnew/
- If you used Intellij 14, download and install the Android SDK
  - Bottom of the page: http://developer.android.com/sdk/index.html
- Make an empty directory (DIR) where you want to keep the app project.
- Navigate to the (DIR) in a terminal.
- Type: 'git clone https://github.com/94bryanr/PartypushAndroid' (without quotes)
- Open the IDE you installed
- File->Import Project
  - Navigate to the Project subfolder in (DIR) and open the project file.

**AUTHORS:**
- Bryan Reilly
  - 970-492-5292
  - 94bryanr@gmail.com
- Jeremy Aldrich
  - 970-227-1969
  - omenzz@gmail.com
-Garrett Phoonswadi
 -303-842-2876
 -garrett.phoonswadi@gmail.com
**GROUP:**
- Wunderlist:
  - Text Bryan Reilly at 970-492-5292 for list invite.
- GroupMe:
  - Text Bryan Reilly at 970-492-5292 for group invite.

**ANDROID BASICS:**
- _Best Reference:_ http://developer.android.com/training/index.html

Activity
- Contains Fragments
- Can think of as a single screen.
- http://developer.android.com/guide/components/activities.html

Fragment
- Contains Views
- Can think of as a portion of the user iterface.
- http://developer.android.com/guide/components/fragments.html

View
- A single UI element.
- Textbox, image, button, etc.

**USING GIT:**
- _Basics:_ http://www.git-tower.com/learn/ebook/command-line/basics/getting-ready#start
- _Branching:_ http://www.git-tower.com/learn/ebook/command-line/branching-merging/branching-can-change-your-life#start
- _Pro Git:_ http://git-scm.com/book/en/v2
- _If you don't like reading:_ 
  - https://pcottle.github.io/learnGitBranching/
  - https://try.github.io/levels/1/challenges/1
  - https://rogerdudler.github.io/git-guide/

Try to use Git out of a terminal, it makes everything easier.

Should understand these terms:
- Commit
- Repository
- Branch
- Merge
- Merge Conflict
- Pull

Should understand these commands:
- git clone [URL]
- git remote
- git remote add [URL]
- git status
- git add [FILE]
- git add -A
- git rm [FILE]
- git commit -m 'COMMIT MESSAGE'
- git branch
- git branch [NEW BRANCH]
- git checkout [BRANCH]
- git merge [BRANCH]
- git pull [REMOTE] [BRANCH]

We will use branches for EVERYTHING.

Recap:
- Getting the repository
  - git clone https://github.com/94bryanr/PartypushAndroid
- Adding repository to remotes list
  - git remote add project https://github.com/94bryanr/PartypushAndroid
- Starting a branch and moving to it
  - git branch coolNewFeature
  - git checkout coolNewFeature
- Commiting a change to your current branch
  - git add -A
  - git commit -m 'added chululu sounds'
  - git push project coolNewFeature
- Merging your branch (once it is finished!!)
  - git checkout master
  - git merge coolNewFeature
- Deleting your branch (after merging)
  - git branch -d coolNewFeature


**INFO:**
- Project started in 2015.
- Read LICENSE for copyright and licensing information.
