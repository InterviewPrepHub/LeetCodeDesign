package com.example.LeetCodeDesign.Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserAccessManager {

    Map<Users, Map<Resource, Set<Permission>>> accessControlList;

    UserAccessManager() {
        accessControlList = new HashMap<>();
    }

    // grant permission to a user for a resource with a given scope
    public void grantPermission(Users user, Resource resource, Permission permission, HierarchyEntity scope) {
        if(!accessControlList.containsKey(user)) {
            accessControlList.put(user, new HashMap<>());
        }
        Map<Resource, Set<Permission>> userPermissions = accessControlList.get(user);

        if(!userPermissions.containsKey(resource)) {
            userPermissions.put(resource, new HashSet<>());
        }
        userPermissions.get(resource).add(permission);
    }



    //to get accessible hirerachy

//    user -> map<Resource, permission

    public Set<HierarchyEntity> getAccessibleHirerachy(Users user, Resource resource, Permission permission) {
        Set<HierarchyEntity> accessibleHirerachy = new HashSet<>();
        for (Map.Entry<Users, Map<Resource, Set<Permission>>> entry: accessControlList.entrySet()) {
            if(entry.getKey().equals(user)) {
                Map<Resource, Set<Permission>> userPermissions = entry.getValue();
                if(userPermissions.containsKey(resource) && userPermissions.get(resource).contains(permission)){
                    HierarchyEntity parent = user.getParent();

                    while(parent!=null) {
                        accessibleHirerachy.add(parent);
                        parent = parent.getParent();
                    }

                    break;
                }
            }
        }

        return accessibleHirerachy;
    }

    public static void main(String[] args) {
        HierarchyEntity tata = new HierarchyEntity("Tata");
        HierarchyEntity tataMotors = new HierarchyEntity("TATA Motors");
        HierarchyEntity tataMotorsMaharashtra = new HierarchyEntity("TATA Motors Maharashtra");
        HierarchyEntity pune = new HierarchyEntity("Pune");

        tata.addChild(tataMotors);
        tataMotors.addChild(tataMotorsMaharashtra);
        tataMotorsMaharashtra.addChild(pune);

        Users userA = new Users("UserA", tataMotors);
        Users userB = new Users("UserB", tataMotorsMaharashtra);


        Resource invoice = new Resource("Invoice");

        UserAccessManager uam = new UserAccessManager();

//        uam.grantPermission(userA, invoice, Permission.READ, tataMotors);
//        uam.grantPermission(userA, invoice, Permission.WRITE, tataMotors);
//        uam.grantPermission(userA, invoice, Permission.FILING, tataMotors);


        uam.grantPermission(userA, invoice, Permission.WRITE, tataMotorsMaharashtra);
        uam.grantPermission(userB, invoice, Permission.FILING, tataMotorsMaharashtra);


        // Accessible Hierarchy
        Set<HierarchyEntity> accessibleHierarchy = uam.getAccessibleHirerachy(userA, invoice, Permission.WRITE);
        for (HierarchyEntity entity : accessibleHierarchy) {
            System.out.println(entity.getName());
        }


    }


}

enum Permission{
    READ, WRITE, FILING;
}

class Users {
    private String name;
    private HierarchyEntity parent;

    public Users(String name, HierarchyEntity parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public HierarchyEntity getParent() {
        return parent;
    }
}

class Resource {
    private String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


class HierarchyEntity {
    private String name;
    private HierarchyEntity parent;
    private Set<HierarchyEntity> children;

    public HierarchyEntity(String name) {
        this.name = name;
        this.children = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public HierarchyEntity getParent() {
        return parent;
    }

    public void setParent(HierarchyEntity parent) {
        this.parent = parent;
    }

    public Set<HierarchyEntity> getChildren() {
        return children;
    }

    public void addChild(HierarchyEntity child) {
        children.add(child);
        child.setParent(this);
    }

    public boolean isAncestor(HierarchyEntity entity) {
        if (this == entity)
            return true;
        if (parent == null)
            return false;
        return parent.isAncestor(entity);
    }
}


/*
Building a User Access Management system based on a company’s hierarchical structure.

User

HierarchyEntity
TATA
TATA Steel
TS Karnataka
TSK Bangalore
TSK Mangalore
TS Gujarat
TSG Ahmedabad
TATA Motors
™ Maharashtra
Mumbai
Pune

Resource
Invoice
Permission
READ
WRITE
FILING

UserA
Has administrative access to all invoices of TATA Motors
User A, TATA Motors, Invoice, [READ, WRITE, FILING]
UserB
Has administrative access to all invoices of TATA Motors Pune - if we are in the lowest level, then we do not have exclusive acces to one level above
User B, TATA Motors Maharashtra Pune, Invoice, [READ, WRITE, FILING]

Query Access Pattern
Specific Authorisation Request
User, Resource, Permission, Scope (Hierarcical Entity)
UserA, Invoice, READ, TS Gujarat
True or False
Accessible Hierarchy
User, Resource, Permission
UserB, Invoice, WRITE - get the entire hierarchy itself for user B which writes to TATA Motors Maharashtra Pune
Hierarchy
TATA(False)
TATA Motors(False)
™ Maharashtra (False)
Pune (True)

Assumption
All resources belong to the lowest level of an hierarchy

 */