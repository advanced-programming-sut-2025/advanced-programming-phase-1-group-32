package records;

public record WalkProposal(
        boolean isAllowed,
        String message,
        int energyCost,
        int x,
        int y
) {
}
