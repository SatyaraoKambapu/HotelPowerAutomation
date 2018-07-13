# HotelPowerAutomation
Used Design patterns are,
# Singleton DP
# Factory DP
# Builder DP
# Observer DP
Used JAVA Version.
# Java 8
 Abstract:

A very prestigious chain of hotels is facing a problem of huge consumption of electricity bills for
its electronic equipments. The common equipments, like lights, ACs, etc are currently controlled
manually, by the hotel staff, using manual switches. Hotel Management wants to optimise the
usage of electricity consumption and also ensure that there is no inconvenience caused to the
guests and staff. So, it has installed Motion Sensors at appropriate places and have approached
you to program a Controller which takes inputs from these sensors and controls various
equipments.
The way the hotel equipments are organised and the requirements for the Controller are listed
below:
● A Hotel can have multiple floors
● Each floor can have multiple main corridors and sub corridors
● Both main corridor and sub corridor have one light each
● Both main and sub corridor lights consume 5 units of power when ON
● Both main and sub corridor have independently controllable ACs
● Both main and sub corridor ACs consume 10 units of power when ON
● All the lights in all the main corridors need to be switched ON between 6PM to 6AM,
which is the Night Time slot
● By default, all ACs are switched ON, all the time
● When a motion is detected in one of the sub corridors the corresponding lights need to
be switched ON between 6PM to 6AM (Night Time slot)
● The total power consumption of all the ACs and lights combined should not exceed
(Number of Main corridors * 15) + (Number of sub corridors * 10) units of per floor. Sub
corridor AC could be switched OFF to ensure that the power consumption is not more
than the specified maximum value
● When there is no motion for more than a minute the sub corridor lights should be
switched OFF and AC needs to be switched ON
Motion in sub-corridors is input to the controller, which needs to keep track and optimise the
power consumption.
Write a program that takes input values for Floors, Main corridors, Sub corridors and takes
different external inputs for motion in sub corridors. For each input, the program prints out the
state of all the lights and ACs in the hotel. For simplicity, assume that the controller is operating
at the Night Time
