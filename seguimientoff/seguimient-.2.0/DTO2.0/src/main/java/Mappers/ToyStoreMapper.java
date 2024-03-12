package Mappers;

import Mapping.ToyDTO;
import Model.Toy;
import Model.Type;

public class ToyStoreMapper {
    public static Toy mapFrom(ToyDTO toyStoreDTO){
        return new Toy(toyStoreDTO.name(), (Type) toyStoreDTO.type(),toyStoreDTO.price(),toyStoreDTO.amount());
    }

    public static ToyDTO mapFrom(Toy toy){return  new ToyDTO(toy.getName(), toy.getType(), toy.getPrice(),toy.getAmount()  );}}
