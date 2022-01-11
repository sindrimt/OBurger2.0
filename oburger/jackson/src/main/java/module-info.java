module oburger.jackson {
    requires transitive com.fasterxml.jackson.databind;
    requires transitive oburger.core;

    exports jackson;
    
    opens jackson to oburger.ui, oburger.rest, com.fasterxml.jackson.databind;
}
