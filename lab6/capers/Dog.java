package capers;

import jdk.jshell.execution.Util;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author TODO
*/
public class Dog implements Serializable { // TODO

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = Utils.join(CapersRepository.CAPERS_FOLDER, "dogs");

    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        File file = Utils.join(DOG_FOLDER, name);
        if(!file.exists())
            exitWithError("Dog did not existed");
        Dog dog = Utils.readObject(file, Dog.class);
        return dog;
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");

        File file = Utils.join(DOG_FOLDER, this.name);
        Utils.writeObject(file, this);
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        File file = Utils.join(DOG_FOLDER, this.name);
        if(file.exists()){
            exitWithError("Dog has existed");
            return;
        }

        if(!DOG_FOLDER.exists()){
            DOG_FOLDER.mkdir();
        }
        try{
            file.createNewFile();
        }catch (Exception e){
            exitWithError(e.toString());
        }

        Utils.writeObject(file, this);
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
