{s(X,Y); f(X,Y); alt(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X!=Y.
eq(X,X) :- traj(X).
eq(Z,X) :- eq(Y,X), eq(Z,Y).
alt(Z,X) :- eq(Y,X), alt(Z,Y).
s(X,Z) :- eq(Y,X), s(Y,Z).
f(X,Z) :- eq(Y,X), f(Y,Z).
i(X,Z) :- eq(Y,X), i(Y,Z).
dis(Z,X) :- eq(Y,X), dis(Z,Y).
alt(Z,X) :- alt(Y,X), eq(Z,Y).
eq(Z,X) | alt(Z,X) :- alt(Y,X), alt(Z,Y).
s(X,Z) :- alt(Y,X), s(Y,Z).
f(X,Z) :- alt(Y,X), f(Y,Z).
i(X,Z) | dis(Z,X) :- alt(Y,X), i(Y,Z).
i(X,Z) | dis(Z,X) :- alt(Y,X), dis(Z,Y).
s(X,Z) :- s(X,Y), eq(Z,Y).
s(X,Z) :- s(X,Y), alt(Z,Y).
eq(Z,X) | alt(Z,X) | s(X,Z) :- s(X,Y), s(Y,Z).
i(X,Z) | dis(Z,X) :- s(X,Y), f(Y,Z).
f(X,Z) | i(X,Z) | dis(Z,X) :- s(X,Y), i(Y,Z).
f(X,Z) | i(X,Z) | dis(Z,X) :- s(X,Y), dis(Z,Y).
f(X,Z) :- f(X,Y), eq(Z,Y).
f(X,Z) :- f(X,Y), alt(Z,Y).
i(X,Z) | dis(Z,X) :- f(X,Y), s(Y,Z).
eq(Z,X) | alt(Z,X) | f(X,Z) :- f(X,Y), f(Y,Z).
s(X,Z) | i(X,Z) | dis(Z,X) :- f(X,Y), i(Y,Z).
s(X,Z) | i(X,Z) | dis(Z,X) :- f(X,Y), dis(Z,Y).
i(X,Z) :- i(X,Y), eq(Z,Y).
i(X,Z) | dis(Z,X) :- i(X,Y), alt(Z,Y).
f(X,Z) | i(X,Z) | dis(Z,X) :- i(X,Y), s(Y,Z).
s(X,Z) | i(X,Z) | dis(Z,X) :- i(X,Y), f(Y,Z).
alt(Z,X) | s(X,Z) | f(X,Z) | i(X,Z) | dis(Z,X) :- i(X,Y), dis(Z,Y).
dis(Z,X) :- dis(Y,X), eq(Z,Y).
i(X,Z) | dis(Z,X) :- dis(Y,X), alt(Z,Y).
f(X,Z) | i(X,Z) | dis(Z,X) :- dis(Y,X), s(Y,Z).
s(X,Z) | i(X,Z) | dis(Z,X) :- dis(Y,X), f(Y,Z).
alt(Z,X) | s(X,Z) | f(X,Z) | i(X,Z) | dis(Z,X) :- dis(Y,X), i(Y,Z).
:- s(X,Z), f(X,Z).
:- s(X,Z), alt(Z,X).
:- s(X,Z), i(X,Z).
:- s(X,Z), eq(Z,X).
:- s(X,Z), dis(Z,X).
:- f(X,Z), s(X,Z).
:- f(X,Z), alt(Z,X).
:- f(X,Z), i(X,Z).
:- f(X,Z), eq(Z,X).
:- f(X,Z), dis(Z,X).
:- alt(Z,X), s(X,Z).
:- alt(Z,X), f(X,Z).
:- alt(Z,X), i(X,Z).
:- alt(Z,X), eq(Z,X).
:- alt(Z,X), dis(Z,X).
:- i(X,Z), s(X,Z).
:- i(X,Z), f(X,Z).
:- i(X,Z), alt(Z,X).
:- i(X,Z), eq(Z,X).
:- i(X,Z), dis(Z,X).
:- eq(Z,X), s(X,Z).
:- eq(Z,X), f(X,Z).
:- eq(Z,X), alt(Z,X).
:- eq(Z,X), i(X,Z).
:- eq(Z,X), dis(Z,X).
:- dis(Z,X), s(X,Z).
:- dis(Z,X), f(X,Z).
:- dis(Z,X), alt(Z,X).
:- dis(Z,X), i(X,Z).
:- dis(Z,X), eq(Z,X).