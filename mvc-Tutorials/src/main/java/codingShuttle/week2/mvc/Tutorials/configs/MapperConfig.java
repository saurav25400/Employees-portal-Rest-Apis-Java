package codingShuttle.week2.mvc.Tutorials.configs;

import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

   // @Bean
//    public ModelMapper getModelMapper(){
//        return new ModelMapper();
//    }

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) // Enable matching fields directly
                .setFieldAccessLevel(AccessLevel.PRIVATE) // Allow private field mapping
                .setSkipNullEnabled(true); // Skip mapping when the source property is null
        return modelMapper;
    }


















}
