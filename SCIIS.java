import java.util.*;

class SkillProfile {
    HashMap<String, Integer> skills = new HashMap<>();

    void addSkill(String name, int level) {
        skills.put(name, level);
    }

    int getSkillLevel(String name) {
        return skills.getOrDefault(name, 0);
    }

    void displaySkills() {
        System.out.println("\nYour Skill Profile:");
        skills.forEach((k, v) -> System.out.println(k + " : " + v + "/10"));
    }
}

class RoleRequirement {
    HashMap<String, Integer> requiredSkills = new HashMap<>();

    RoleRequirement(String role) {
        if (role.equalsIgnoreCase("Java Full Stack")) {
            requiredSkills.put("Java", 7);
            requiredSkills.put("SQL", 6);
            requiredSkills.put("DSA", 6);
            requiredSkills.put("Aptitude", 5);
            requiredSkills.put("Communication", 5);
        }
    }
}

class ReadinessEngine {

    static double calculateReadiness(SkillProfile profile) {
        double score =
                profile.getSkillLevel("Java") * 0.3 +
                profile.getSkillLevel("SQL") * 0.2 +
                profile.getSkillLevel("DSA") * 0.3 +
                profile.getSkillLevel("Aptitude") * 0.1 +
                profile.getSkillLevel("Communication") * 0.1;

        return score * 10; // percentage
    }
}

class RecommendationEngine {

    static void showRecommendations(SkillProfile profile, RoleRequirement role) {
        System.out.println("\nSkill Gaps & Recommendations:");

        for (String skill : role.requiredSkills.keySet()) {
            int required = role.requiredSkills.get(skill);
            int current = profile.getSkillLevel(skill);

            if (current < required) {
                System.out.println("❌ " + skill +
                        " (Required: " + required +
                        ", Current: " + current + ")");
                recommend(skill);
            } else {
                System.out.println("✅ " + skill + " meets requirement");
            }
        }
    }

    static void recommend(String skill) {
        switch (skill) {
            case "Java" ->
                    System.out.println("   → Revise OOP, Collections, Exception Handling");
            case "SQL" ->
                    System.out.println("   → Practice Joins, Subqueries, Indexes");
            case "DSA" ->
                    System.out.println("   → Focus on Arrays, Strings, Recursion");
            case "Aptitude" ->
                    System.out.println("   → Practice Quantitative & Logical Reasoning");
            case "Communication" ->
                    System.out.println("   → Improve mock interviews & explanations");
        }
    }
}

public class SCIIS {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        SkillProfile profile = new SkillProfile();

        System.out.println("=== Smart Career & Interview Intelligence System ===");

        addSkillInput(profile, "Java");
        addSkillInput(profile, "SQL");
        addSkillInput(profile, "DSA");
        addSkillInput(profile, "Aptitude");
        addSkillInput(profile, "Communication");

        profile.displaySkills();

        RoleRequirement role = new RoleRequirement("Java Full Stack");

        double readiness = ReadinessEngine.calculateReadiness(profile);
        System.out.printf("\n📊 Interview Readiness: %.2f%%\n", readiness);

        RecommendationEngine.showRecommendations(profile, role);

        System.out.println("\n📅 Suggested 7-Day Roadmap:");
        generateRoadmap(profile, role);
    }

    static void addSkillInput(SkillProfile profile, String skill) {
        System.out.print("Enter " + skill + " skill level (0-10): ");
        int level = sc.nextInt();
        profile.addSkill(skill, level);
    }

    static void generateRoadmap(SkillProfile profile, RoleRequirement role) {
        int day = 1;
        for (String skill : role.requiredSkills.keySet()) {
            if (profile.getSkillLevel(skill) < role.requiredSkills.get(skill)) {
                System.out.println("Day " + day + "-" + (day + 1) +
                        ": Improve " + skill);
                day += 2;
            }
        }
        System.out.println("Final Day: Mock Interview & Revision");
    }
}
