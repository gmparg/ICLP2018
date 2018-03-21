{s(X,Y); f(X,Y); alt(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X<Y, #count{R : fact(R,X,Y)} = 0.
eq(X,X) :- traj(X).
:- eq(X,Y), eq(Y,Z), not eq(X,Z).
:- eq(X,Y), alt(Y,Z), not alt(X,Z).
:- eq(X,Y), s(Y,Z), not s(X,Z).
:- eq(X,Y), f(Y,Z), not f(X,Z).
:- eq(X,Y), i(Y,Z), not i(X,Z).
:- eq(X,Y), dis(Y,Z), not dis(X,Z).
:- alt(X,Y), eq(Y,Z), not alt(X,Z).
:- alt(X,Y), alt(Y,Z), not eq(X,Z), not alt(X,Z).
:- alt(X,Y), s(Y,Z), not s(X,Z).
:- alt(X,Y), f(Y,Z), not f(X,Z).
:- alt(X,Y), i(Y,Z), not i(X,Z), not dis(X,Z).
:- alt(X,Y), dis(Y,Z), not i(X,Z), not dis(X,Z).
:- s(X,Y), eq(Y,Z), not s(X,Z).
:- s(X,Y), alt(Y,Z), not s(X,Z).
:- s(X,Y), s(Y,Z), not eq(X,Z), not alt(X,Z), not s(X,Z).
:- s(X,Y), f(Y,Z), not i(X,Z), not dis(X,Z).
:- s(X,Y), i(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).
:- s(X,Y), dis(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).
:- f(X,Y), eq(Y,Z), not f(X,Z).
:- f(X,Y), alt(Y,Z), not f(X,Z).
:- f(X,Y), s(Y,Z), not i(X,Z), not dis(X,Z).
:- f(X,Y), f(Y,Z), not eq(X,Z), not alt(X,Z), not f(X,Z).
:- f(X,Y), i(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).
:- f(X,Y), dis(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).
:- i(X,Y), eq(Y,Z), not i(X,Z).
:- i(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z).
:- i(X,Y), s(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).
:- i(X,Y), f(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).
:- i(X,Y), dis(Y,Z), not alt(X,Z), not s(X,Z), not f(X,Z), not i(X,Z), not dis(X,Z).
:- dis(X,Y), eq(Y,Z), not dis(X,Z).
:- dis(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z).
:- dis(X,Y), s(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).
:- dis(X,Y), f(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).
:- dis(X,Y), i(Y,Z), not alt(X,Z), not s(X,Z), not f(X,Z), not i(X,Z), not dis(X,Z).
eq(X,Y) :- fact(eq,X,Y).
alt(X,Y) :- fact(alt,X,Y).
i(X,Y) :- fact(i,X,Y).
s(X,Y) :- fact(s,X,Y).
f(X,Y) :- fact(f,X,Y).
dis(X,Y) :- fact(dis,X,Y).
