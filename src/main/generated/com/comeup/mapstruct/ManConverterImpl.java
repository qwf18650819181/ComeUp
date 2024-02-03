package com.comeup.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T16:43:36+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
public class ManConverterImpl implements ManConverter {

    @Override
    public Main.Man toMan(Main.Human bo) {
        if ( bo == null ) {
            return null;
        }

        Main.Man man = new Main.Man();

        if ( bo.getName() != null ) {
            man.setName( String.valueOf( bo.getName() ) );
        }
        man.setId( bo.getId() );

        return man;
    }

    @Override
    public List<Main.Man> toMans(List<Main.Human> list) {
        if ( list == null ) {
            return null;
        }

        List<Main.Man> list1 = new ArrayList<Main.Man>( list.size() );
        for ( Main.Human human : list ) {
            list1.add( toMan( human ) );
        }

        return list1;
    }
}
