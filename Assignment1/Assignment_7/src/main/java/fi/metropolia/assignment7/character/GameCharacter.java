package fi.metropolia.assignment7.character;

import fi.metropolia.assignment7.state.CharacterState;
import fi.metropolia.assignment7.state.NoviceState;

/**
 * GameCharacter class that holds the character's attributes and current state.
 * This is the Context class in the State design pattern.
 */
public class GameCharacter {
    
    private String name;
    private int level;
    private int experiencePoints;
    private int healthPoints;
    private int maxHealthPoints;
    private CharacterState currentState;
    
    // Experience thresholds for level ups
    public static final int INTERMEDIATE_THRESHOLD = 100;
    public static final int EXPERT_THRESHOLD = 300;
    public static final int MASTER_THRESHOLD = 600;
    
    public GameCharacter(String name) {
        this.name = name;
        this.level = 1;
        this.experiencePoints = 0;
        this.maxHealthPoints = 100;
        this.healthPoints = maxHealthPoints;
        this.currentState = new NoviceState();
    }
    
    // Actions delegated to current state
    public void train() {
        currentState.train(this);
        currentState.checkLevelUp(this);
    }
    
    public void meditate() {
        currentState.meditate(this);
        currentState.checkLevelUp(this);
    }
    
    public void fight() {
        currentState.fight(this);
        currentState.checkLevelUp(this);
    }
    
    // Display character status
    public void displayStatus() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         CHARACTER STATUS               ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║  Name: %-31s ║%n", name);
        System.out.printf("║  Level: %-30s ║%n", currentState.getLevelName());
        System.out.printf("║  Experience: %-25d ║%n", experiencePoints);
        System.out.printf("║  Health: %d/%d %-21s ║%n", healthPoints, maxHealthPoints, getHealthBar());
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    private String getHealthBar() {
        int bars = (int) ((double) healthPoints / maxHealthPoints * 10);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 10; i++) {
            sb.append(i < bars ? "█" : "░");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void displayAvailableActions() {
        System.out.println("\nAvailable Actions:");
        String[] actions = currentState.getAvailableActions();
        for (int i = 0; i < actions.length; i++) {
            System.out.printf("  %d. %s%n", i + 1, actions[i]);
        }
        System.out.println("  0. Quit Game");
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getExperiencePoints() {
        return experiencePoints;
    }
    
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }
    
    public void addExperience(int amount) {
        this.experiencePoints += amount;
        System.out.printf("  ⟶ Gained %d experience points!%n", amount);
    }
    
    public int getHealthPoints() {
        return healthPoints;
    }
    
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.min(healthPoints, maxHealthPoints);
        this.healthPoints = Math.max(this.healthPoints, 0);
    }
    
    public void addHealth(int amount) {
        int oldHealth = healthPoints;
        setHealthPoints(healthPoints + amount);
        int gained = healthPoints - oldHealth;
        if (gained > 0) {
            System.out.printf("  ⟶ Restored %d health points!%n", gained);
        }
    }
    
    public void reduceHealth(int amount) {
        setHealthPoints(healthPoints - amount);
        System.out.printf("  ⟶ Lost %d health points!%n", amount);
    }
    
    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }
    
    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }
    
    public CharacterState getCurrentState() {
        return currentState;
    }
    
    public void setState(CharacterState state) {
        this.currentState = state;
        this.level++;
        System.out.println("\n★★★ LEVEL UP! ★★★");
        System.out.printf("You have reached the %s level!%n", state.getLevelName());
    }
    
    public boolean isAlive() {
        return healthPoints > 0;
    }
    
    public boolean isMaster() {
        return currentState.getLevelName().equals("Master");
    }
}

