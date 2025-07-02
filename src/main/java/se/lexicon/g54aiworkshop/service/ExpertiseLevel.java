package se.lexicon.g54aiworkshop.service;

public enum ExpertiseLevel {
    BEGINNER("Explain in simple terms with basic examples."),
    INTERMEDIATE("Provide practical examples and best practices."),
    ADVANCED("Discuss advanced concepts, trade-offs and optimizations.");

    private final String instruction;

    ExpertiseLevel(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }
}
