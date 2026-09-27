# PocketPilot 💰

A JavaFX-based personal finance management app — built as a semester project to actually understand how a real desktop application comes together, not just write another console program.

## Why I built it

I wanted a project that wasn't just "print statements and loops." I wanted multiple screens, real navigation between them, a login system, and actual data that persists — something closer to what a real app looks like under the hood. Personal finance felt like a good fit because everyone understands the problem: track income, track expenses, set a budget, see where the money's going.

## What it does

- **Multi-user login & registration** — each user has their own data
- **Income tracking** — log and categorize income
- **Expense tracking** — log and categorize spending
- **Budget management** — set and monitor budgets
- **Reports** — a dedicated screen to review your finances
- **Profile management**
- **Dashboard** — central hub after login

## How it's built

- **JavaFX + FXML** for UI, laid out with Scene Builder
- **Separate model classes** for data (not mixed into controllers)
- **Separate file-handling layer** for persistence — no database, but the read/write logic isn't dumped into the UI code
- **Custom SceneNavigator** for switching between screens cleanly instead of hacky scene-swapping
- **SessionManager** to handle the logged-in user across the app
- **Custom CSS styling** so it doesn't look like a default JavaFX app

## Honest take

This isn't a toy script — there's real separation between UI, data, and file handling, and it required actually thinking through navigation and session state across multiple screens, which most beginner projects skip.

Where it's not "production-ready" yet:
- Persistence is file-based, not a real database
- No automated tests yet
- Could use a cleaner service-layer boundary between models and file I/O

For a semester project, though, it's solid — functional, multi-screen, multi-user, and structured in a way I could actually explain and extend later, not just something I copy-pasted together.

## Stack

`Java` · `JavaFX` · `FXML` · `Scene Builder` · `Maven`

## Status

Actively built during coursework. Core features complete; polish and possible DB migration are future ideas.
