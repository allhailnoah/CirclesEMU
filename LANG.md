CHK - conditional statement
00001
<check type>	see CircleCommands.ConditionType
<1st cell ID>	1st cell to compare
<2nd cell ID>	2nd cell to compare


FWD - skip next x cartridge statements
00010
<how much>	number of statements to forward the pointer


BCK - go x cartridge statements back
00011
<how much>	number of statements to backward the pointer


SET - set memory cell's value to specified number
00100
<value>
<cell ID>


ADD - add specified number to memory cell's value
00101
<value>
<cell ID>


SUB - subtract specified number from memory cell's value
00110
<value>
<cell ID>


[empty statement] - no use for 00111


