package com.example.LeetCodeDesign.Design.ClearTax;

import java.util.*;

public class Controller {

    List<UserPermissions> userPermissions;
    HashMap<Integer, Levels> levelMap;

    Controller() {
        userPermissions = new ArrayList<>();
        levelMap = new HashMap<>();
    }

    public void addLevel(int levelId, Integer parentLevelId) {
        levelMap.put(levelId, new Levels(levelId, parentLevelId));
    }

    public boolean assignPermission(int userId, int permissionId, int levelId) {
        userPermissions.add(new UserPermissions(userId, permissionId, levelId));
        return true;
    }

    private boolean hasPermission(int userId, int permissionId, int levelId) {
        Set<Integer> accessibleLevels = new HashSet<>();
        collectLevels(levelId, accessibleLevels);

        return userPermissions.stream().anyMatch(p ->
                p.getUserId() == userId && p.getPermissionId() == permissionId && accessibleLevels.contains(p.getLevelId()));
    }

    private void collectLevels(int levelId, Set<Integer> accessibleLevels) {

        //moving upwards through all parent levels and also checking all levels that could inherit permissions from their parents

        collectParentLevels(levelId, accessibleLevels); // collect upwards

        Set<Integer> findLevelsDownwards = new HashSet<>(accessibleLevels);

        findLevelsDownwards.forEach(l -> collectChildLevels(l, accessibleLevels));    //collect downwards
    }

    private void collectParentLevels(int levelId, Set<Integer> accessibleLevels) {
        Integer currentLevelId = levelId;

        while (currentLevelId != null) {
            accessibleLevels.add(currentLevelId);
            Levels currentLevel = levelMap.get(currentLevelId);
            currentLevelId = currentLevel.getParentLevelId(); // Move to the parent level
        }
    }

    private void collectChildLevels(int levelId, Set<Integer> accessibleLevels) {
        levelMap.values().stream()
                .filter(l -> Objects.equals(l.getParentLevelId(), levelId))
                .forEach(l -> {
                    accessibleLevels.add(l.getLevelId());
                    collectChildLevels(l.getLevelId(), accessibleLevels); // Recursively collect all children
                });
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.addLevel(1, null);  // Root
        controller.addLevel(2, 1);     // Child of Root
        controller.addLevel(3, 2);     // Child of 2

        // Assign permissions
        controller.assignPermission(1, 101, 1); // User 1 gets permission 101 at level 1

        // Check permissions
        boolean res1 = controller.hasPermission(1, 101, 1);
        boolean res2 = controller.hasPermission(1, 101, 3);
        boolean res3 = controller.hasPermission(1, 102, 1);

        System.out.println("Does user 1 have permission 101 at level 1? " + res1);
        System.out.println("Does user 1 have permission 101 at level 3? " + res2); // true due to cascading
        System.out.println("Does user 1 have permission 102 at level 1? " + res3); // False, no permission 102 assigned
    }

}
