# Cron Parser

This application implements a simple command line application for parsing a cron expression.

Given a cron expression in commandline arguments and/or from the main app it should process then print the cron expression.

## Dependencies

- JDK 11
- Gradle 6

## Running the application
./gradlew run

## Running the test
./gradlew clean test

## TODOs
- Better error handling and input validation
- Not implemented L W
- Update test coverage so all methods are covered for all fields.


#References
The reference documentation for this implementation is found at https://en.wikipedia.org/wiki/Cron#CRON_expression and pasted
below:


Field name     Mandatory?   Allowed values    Allowed special characters
----------     ----------   --------------    --------------------------
Seconds        No           0-59              * / , -
Minutes        Yes          0-59              * / , -
Hours          Yes          0-23              * / , -
Day of month   Yes          1-31              * / , - L W
Month          Yes          1-12 or JAN-DEC   * / , -
Day of week    Yes          0-6 or SUN-SAT    * / , - L #
Year           No           1970–2099         * / , -
