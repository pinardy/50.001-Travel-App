import java.util.*;

/**
 * Created by Pin on 06-Nov-16.
 */
public class ExhaustiveEnum {

    public enum MODE {
        WALK, PUBLIC, TAXI;
    }
    public enum DESTINATION_TO {
        MARINA_BAY_SANDS, SINGAPORE_FLYER, VIVO_CITY,
        RESORTS_WORLD_SENTOSA, BUDDHA_TOOTH_RELIC_TEMPLE, ZOO;
    }
    public enum DESTINATION_FROM {
        MARINA_BAY_SANDS, SINGAPORE_FLYER, VIVO_CITY,
        RESORTS_WORLD_SENTOSA, BUDDHA_TOOTH_RELIC_TEMPLE, ZOO;
    }

    public static List<List<Enum>> permutation(Enum[]... enums) {
        return permutation(new ArrayList<>(Arrays.asList(enums)));
    }

    public static List<List<Enum>> permutation(List<Enum[]> enums) {
        if (enums.isEmpty()) {
            //Trivial case of recursive function
            return new ArrayList<>();
        }
        //remove first element
        Enum[] myEnums = enums.remove(0);
        List<List<Enum>> out = new ArrayList<>();
        for (Enum e : myEnums) {
            // recursive call
            List<List<Enum>> list = permutation(enums);

            //for each list get from recursion adding element e
            for (List<Enum> list_enum : list) {
                list_enum.add(0, e);
                out.add(list_enum);
            }
            if(list.isEmpty()){
                List<Enum> list_enum = new ArrayList<>();
                list_enum.add(e);
                out.add(list_enum);
            }
        }
        enums.add(0, myEnums); //Backtracking
        return out;
    }

    public static void main(String[] args) {
        // pass each values in enums
        List permutationList = permutation(MODE.values(),
                DESTINATION_FROM.values(),  DESTINATION_TO.values());
        System.out.println(permutationList);
    }
}


