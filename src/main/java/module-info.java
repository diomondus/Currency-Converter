/**
 * Created by Dmitry Butilov
 * on 23.02.18.
 */
open module com.butilov {
    requires jackson.annotations;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.core;
    requires spring.context;

    requires java.sql;

//    opens com.butilov to spring.core, spring.beans, spring.context;
//    opens com.butilov.services to spring.core, spring.beans, spring.context;
//    opens com.butilov.controller to spring.core, spring.beans, spring.context;
}