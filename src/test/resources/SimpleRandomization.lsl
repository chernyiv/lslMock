libsl "1.0.0";
library SimpleRandomization version "1.0.0";

types {
    Address (Address);
    Data (Data);
    Job (Job);
    Person (Person);
    Execution (Execution);
    String (java.lang.String);
}

automaton Address : Address {
    initstate Created;

    fun toString(): String;
}

automaton Data : Data {
    initstate Created;

    fun toString(): String;
}

automaton Job : Job {
    initstate Created;

    fun toString(): String;
}

automaton Person : Person {
    initstate Created;

    fun toString(): String;
}

automaton Execution : Execution {
    initstate Created;

    fun setAndReturnData(): Data;
}
