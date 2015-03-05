# PartypushAndroid
Android version of Partypush

**Project Sub-Modules and Libraries:**

- _Assets:_ Custom images.
  
- _OldProject:_ Old verson of the app that might contain useful code snippets. Not in development. Will be removed soon.
  
- _Plan:_ Documents showing the high level structure of the program.
  
- _Project:_ Source code, this is what ships.
  

**INSTALL:**
- TODO: instructions to install, configure, build, and run the program

**AUTHORS:**
- Bryan Reilly
  - 970-492-5292
  - 94bryanr@gmail.com
- Jeremy Aldrich
  - 970-227-1969
  - omenzz@gmail.com

**GROUP:**
- Wunderlist:
  - Text Bryan Reilly at 970-492-5292 for list invite.
- GroupMe:
  - Text Bryan Reilly at 970-492-5292 for group invite.

**USING GIT:**
- Basics: http://www.git-tower.com/learn/ebook/command-line/basics/getting-ready#start
- Branching: http://www.git-tower.com/learn/ebook/command-line/branching-merging/branching-can-change-your-life#start

Do not get a GUI application, do it out of a terminal.

Must understand these terms:
- Commit
- Repository
- Branch
- Merge

Must understand these commands:
- git clone [URL]
- git remote
- git remote add [URL]
- git status
- git add [FILE]
- git rm [FILE]
- git commit -m 'COMMIT MESSAGE'
- git branch
- git branch [NEW BRANCH]
- git checkout [BRANCH]
- get merge [BRANCH]

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
  - git add -A       <--Adds all new and modified files to staging area
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
