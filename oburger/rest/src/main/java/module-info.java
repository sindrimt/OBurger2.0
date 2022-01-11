module oburger.rest {
    requires oburger.core;
    requires oburger.jackson;
    requires spring.boot;
    requires spring.web;
    requires spring.boot.autoconfigure;

    requires spring.beans;
    requires spring.context;

    exports springboot;
}
