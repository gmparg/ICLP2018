{true(X,R,Y) : relation(R)} = 1 :- element(X); element(Y); X != Y.
true(X,eq,X) :- element(X).
:- true(X,R1,Y); true(Y,R2,Z); not true(X,Rout,Z) : table(R1,R2,Rout).
:- possible(X,_,Y); not true(X,R,Y) : possible(X,R,Y).
relation(eq; alt; s; f; i; dis).
table(eq, eq, (eq)).
table(eq, alt, (alt)).
table(eq, s, (s)).
table(eq, f, (f)).
table(eq, i, (i)).
table(eq, dis, (dis)).
table(alt, eq, (alt)).
table(alt, alt, (eq;alt)).
table(alt, s, (s)).
table(alt, f, (f)).
table(alt, i, (i;dis)).
table(alt, dis, (i;dis)).
table(s, eq, (s)).
table(s, alt, (s)).
table(s, s, (eq;alt;s)).
table(s, f, (i;dis)).
table(s, i, (f;i;dis)).
table(s, dis, (f;i;dis)).
table(f, eq, (f)).
table(f, alt, (f)).
table(f, s, (i;dis)).
table(f, f, (eq;alt;f)).
table(f, i, (s;i;dis)).
table(f, dis, (s;i;dis)).
table(i, eq, (i)).
table(i, alt, (i;dis)).
table(i, s, (f;i;dis)).
table(i, f, (s;i;dis)).
table(i, i, (eq;alt;s;f;i;dis)).
table(i, dis, (alt;s;f;i;dis)).
table(dis, eq, (dis)).
table(dis, alt, (i;dis)).
table(dis, s, (f;i;dis)).
table(dis, f, (s;i;dis)).
table(dis, i, (alt;s;f;i;dis)).
table(dis, dis, (eq;alt;s;f;i;dis)).
