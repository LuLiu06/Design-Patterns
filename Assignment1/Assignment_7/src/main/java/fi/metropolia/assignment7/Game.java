package fi.metropolia.assignment7;

import fi.metropolia.assignment7.character.GameCharacter;

import java.util.Scanner;

/**
 * Main Game class that runs the game loop.
 * Demonstrates the State design pattern through character progression.
 */
public class Game {
    
    private final Scanner scanner;
    private GameCharacter character;
    
    public Game() {
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        printWelcome();
        createCharacter();
        gameLoop();
        printGameOver();
    }
    
    private void printWelcome() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     GAME CHARACTER DEVELOPMENT SYSTEM                ║");
        System.out.println("║     ─────────────────────────────────                ║");
        System.out.println("║     Train, Meditate, Fight, and become a Master!     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
    
    private void createCharacter() {
        System.out.print("\nEnter your character's name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Hero";
        }
        character = new GameCharacter(name);
        System.out.printf("%nWelcome, %s! Your journey begins as a Novice.%n", name);
        System.out.println("Train hard to unlock new abilities and reach the Master level!");
    }
    
    private void gameLoop() {
        while (character.isAlive() && !character.isMaster()) {
            character.displayStatus();
            character.displayAvailableActions();
            
            int choice = getPlayerChoice();
            
            if (choice == 0) {
                System.out.println("\nYou have chosen to end your journey...");
                break;
            }
            
            executeAction(choice);
            
            // Check if character died
            if (!character.isAlive()) {
                System.out.println("\n☠ Your character has fallen in battle!");
                System.out.println("The journey ends here...");
            }
        }
    }
    
    private int getPlayerChoice() {
        System.out.print("\nChoose an action: ");
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void executeAction(int choice) {
        String[] actions = character.getCurrentState().getAvailableActions();
        
        switch (choice) {
            case 1:
                character.train();
                break;
            case 2:
                if (actions.length >= 2) {
                    character.meditate();
                } else {
                    System.out.println("\nInvalid action!");
                }
                break;
            case 3:
                if (actions.length >= 3) {
                    character.fight();
                } else {
                    System.out.println("\nInvalid action!");
                }
                break;
            default:
                System.out.println("\nInvalid action! Please choose a valid option.");
        }
    }
    
    private void printGameOver() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                    GAME OVER                         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        character.displayStatus();
        
        if (character.isMaster()) {
            System.out.println("\n🎉 CONGRATULATIONS! 🎉");
            System.out.printf("%s has achieved the rank of MASTER!%n", character.getName());
            System.out.println("You have completed your journey and mastered all skills!");
        } else if (!character.isAlive()) {
            System.out.printf("%n%s fell in battle with %d experience points.%n", 
                    character.getName(), character.getExperiencePoints());
            System.out.println("Better luck next time!");
        } else {
            System.out.printf("%nThanks for playing, %s!%n", character.getName());
        }
        
        System.out.println("\n════════════════════════════════════════════════════════");
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

