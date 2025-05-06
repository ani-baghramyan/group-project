# Space Encyclopedia group-project

## Overview
**Space Encyclopedia** is a Java-based collaborative project designed to catalog and provide detailed information about Stars, Planets, and Galaxies. The goal is to make learning about space engaging, educational, and accessible for everyone. The application supports structured storage, search, and retrieval of astronomical data, along with file-based persistence.

## Features
- Load, save, and manage celestial data from a file ("space_objects.txt")
- Structured representations of Planets, Stars, and Galaxies
- Search functionality for all celestial objects by name
- Sorted storage based on natural ordering
- Sample data generation on first run

## Core Classes

### Galaxy
Represents galaxies in the universe, encapsulating:
- Name, type, mass, temperature
- Number of stars, diameter, age

Functionality:
- Stores and retrieves information
- Classifies by galaxy type (spiral, elliptical, etc.)
- Compares by physical properties like mass and number of stars


### Planet
Represents planets, encapsulating:
- Name, type, mass, temperature
- Atmosphere, surface conditions
- Number of moons, radius

Functionality:
- Stores and retrieves planetary details
- Compares by characteristics such as atmosphere or size
- Evaluates potential habitability

### Star
Represents stars, encapsulating:
- Name, type, temperature, mass
- Color, luminosity, size

Functionality:
- Stores and retrieves star details
- Classifies by star type (e.g., yellow dwarf, red giant)
- Compares based on luminosity and temperature

## DataManager
Manages all celestial object data:
- Loads data from a file or creates sample objects
- Saves all data back to file in a structured format
- Maintains separate, sorted collections for stars, planets, and galaxies
- Provides search functionality by name for all celestial types
