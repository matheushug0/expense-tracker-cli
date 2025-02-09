package model;

public enum Category {
    FOOD("Food", 1),
    TRANSPORT("Transport", 2),
    EDUCATION("Education", 3),
    ENTERTAINMENT("Entertainment", 4),
    INVESTIMENT("Investiment", 5),
    OTHER("Other", 0);

    String name;
    int id;

    Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Category fromId(int id) {
        for (Category category : Category.values()) {
            if (category.getId() == id) {
                return category;
            }
        }
        return Category.OTHER;
    }
}
