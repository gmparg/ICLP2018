{ s(X,Z) ; f(X,Z) ; ex(X,Z); ex(Z,X) ; alt(X,Z) ; ret(X,Z) ; rev(X,Z) ;  i(X,Z) ; eq(X,Z) ; dis(X,Z) }=1 :- traj(X), traj(Z), X!=Z.
eq(X,X) :- traj(X).
eq(X,Z) :- eq(X,Y), eq(Y,Z).
rev(X,Z) :- eq(X,Y), rev(Y,Z).
alt(X,Z) :- eq(X,Y), alt(Y,Z).
ret(X,Z) :- eq(X,Y), ret(Y,Z).
s(X,Z) :- eq(X,Y), s(Y,Z).
f(X,Z) :- eq(X,Y), f(Y,Z).
ex(X,Z) :- eq(X,Y), ex(Y,Z).
ex(Z,X) :- eq(X,Y), ex(Z,Y).
i(X,Z) :- eq(X,Y), i(Y,Z).
dis(X,Z) :- eq(X,Y), dis(Y,Z).
rev(X,Z) :- rev(X,Y), eq(Y,Z).
eq(X,Z) :- rev(X,Y), rev(Y,Z).
ret(X,Z) :- rev(X,Y), alt(Y,Z).
alt(X,Z) :- rev(X,Y), ret(Y,Z).
ex(Z,X) :- rev(X,Y), s(Y,Z).
ex(X,Z) :- rev(X,Y), f(Y,Z).
f(X,Z) :- rev(X,Y), ex(Y,Z).
s(X,Z) :- rev(X,Y), ex(Z,Y).
i(X,Z) :- rev(X,Y), i(Y,Z).
dis(X,Z) :- rev(X,Y), dis(Y,Z).
alt(X,Z) :- alt(X,Y), eq(Y,Z).
ret(X,Z) :- alt(X,Y), rev(Y,Z).
eq(X,Z) | alt(X,Z)  :- alt(X,Y), alt(Y,Z).
rev(X,Z) | ret(X,Z)  :- alt(X,Y), ret(Y,Z).
s(X,Z) :- alt(X,Y), s(Y,Z).
f(X,Z) :- alt(X,Y), f(Y,Z).
ex(X,Z) :- alt(X,Y), ex(Y,Z).
ex(Z,X) :- alt(X,Y), ex(Z,Y).
i(X,Z) | dis(X,Z) :- alt(X,Y), i(Y,Z).
i(X,Z) | dis(X,Z) :- alt(X,Y), dis(Y,Z).
ret(X,Z) :- ret(X,Y), eq(Y,Z).
alt(X,Z) :- ret(X,Y), rev(Y,Z).
rev(X,Z) | ret(X,Z)  :- ret(X,Y), alt(Y,Z).
eq(X,Z) | alt(X,Z)  :- ret(X,Y), ret(Y,Z).
ex(Z,X) :- ret(X,Y), s(Y,Z).
ex(X,Z) :- ret(X,Y), f(Y,Z).
f(X,Z) :- ret(X,Y), ex(Y,Z).
s(X,Z) :- ret(X,Y), ex(Z,Y).
i(X,Z) | dis(X,Z) :- ret(X,Y), i(Y,Z).
i(X,Z) | dis(X,Z) :- ret(X,Y), dis(Y,Z).
s(X,Z) :- s(X,Y), eq(Y,Z).
ex(X,Z) :- s(X,Y), rev(Y,Z).
s(X,Z) :- s(X,Y), alt(Y,Z).
ex(X,Z) :- s(X,Y), ret(Y,Z).
eq(X,Z) | alt(X,Z) | s(X,Z) :- s(X,Y), s(Y,Z).
exi(Z,X) | i(X,Z) | dis(X,Z) :- s(X,Y), f(Y,Z).
rev(X,Z) | ret(X,Z) | ex(X,Z):- s(X,Y), ex(Y,Z).
f(X,Z) | i(X,Z) | dis(X,Z) :- s(X,Y), ex(Z,Y).
f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- s(X,Y), i(Y,Z).
f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- s(X,Y), dis(Y,Z).
f(X,Z) :- f(X,Y), eq(Y,Z).
ex(Z,X) :- f(X,Y), rev(Y,Z).
f(X,Z) :- f(X,Y), alt(Y,Z).
ex(Z,Y) :- f(X,Y), ret(Y,Z).
ex(X,Z) | i(X,Z) | dis(X,Z) :- f(X,Y), s(Y,Z).
eq(X,Z) | alt(X,Z) | f(X,Z) :- f(X,Y), f(Y,Z).
s(X,Z) | i(X,Z) | dis(X,Z):- f(X,Y), ex(Y,Z).
rev(X,Z) | ret(X,Z) | ex(Z,X) :- f(X,Y), ex(Z,Y).
s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- f(X,Y), i(Y,Z).
s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- f(X,Y), dis(Y,Z).
ex(X,Z) :- ex(X,Y), eq(Y,Z).
s(X,Z) :- ex(X,Y), rev(Y,Z).
ex(X,Z) :- ex(X,Y), alt(Y,Z).
s(X,Z) :- ex(X,Y), ret(Y,Z).
f(X,Z) | i(X,Z) | dis(X,Z) :- ex(X,Y), s(Y,Z).
rev(X,Z) | ret(X,Z) | ex(X,Z) :- ex(X,Y), f(Y,Z).
ex(Z,X) | i(X,Z) | dis(X,Z):- ex(X,Y), ex(Y,Z).
eq(X,Z) | alt(X,Z) | s(X,Z) :- ex(X,Y), ex(Z,Y).
f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- ex(X,Y), i(Y,Z).
f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- ex(X,Y), dis(Y,Z).
ex(Z,X) :- ex(Y,X), eq(Y,Z).
f(X,Z) :- ex(Y,X), rev(Y,Z).
ex(Z,X) :- ex(Y,X), alt(Y,Z).
f(X,Z) :- ex(Y,X), ret(Y,Z).
rev(X,Z) | ret(X,Z) | ex(Z,X) :- ex(Y,X), s(Y,Z).
s(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), f(Y,Z).
eq(X,Z) | alt(X,Z) | f(X,Z):- ex(Y,X), ex(Y,Z).
ex(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), ex(Z,Y).
s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), i(Y,Z).
s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), dis(Y,Z).
i(X,Z) :- i(X,Y), eq(Y,Z).
i(X,Z) :- i(X,Y), rev(Y,Z).
i(X,Z)| dis(X,Z) :- i(X,Y), alt(Y,Z).
i(X,Z)| dis(X,Z) :- i(X,Y), ret(Y,Z).
f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- i(X,Y), s(Y,Z).
s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- i(X,Y), f(Y,Z).
s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- i(X,Y), ex(Y,Z).
f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z):- i(X,Y), ex(Z,Y).
:- i(X,Y), dis(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z), not f(X,Z), not ex(X,Z), not ex(Z,X), not i(X,Z), not dis(X,Z).
dis(X,Z) :- dis(X,Y), eq(Y,Z).
dis(X,Z) :- dis(X,Y), rev(Y,Z).
i(X,Z)| dis(X,Z) :- dis(X,Y), alt(Y,Z).
i(X,Z)| dis(X,Z) :- dis(X,Y), ret(Y,Z).
f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- dis(X,Y), s(Y,Z).
s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- dis(X,Y), f(Y,Z).
s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- dis(X,Y), ex(Y,Z).
f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z):- dis(X,Y), ex(Z,Y).
:- dis(X,Y), i(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z), not f(X,Z), not ex(X,Z), not ex(Z,X), not i(X,Z), not dis(X,Z).
:- s(X,Z), f(X,Z).
:- s(X,Z), alt(X,Z).
:- s(X,Z), i(X,Z).
:- s(X,Z), eq(X,Z).
:- s(X,Z), dis(X,Z).
:- s(X,Z), ex(X,Z).
:- s(X,Z), ex(Z,X).
:- s(X,Z), rev(X,Z).
:- s(X,Z), ret(X,Z).
:- f(X,Z), alt(X,Z).
:- f(X,Z), i(X,Z).
:- f(X,Z), eq(X,Z).
:- f(X,Z), dis(X,Z).
:- f(X,Z), ex(X,Z).
:- f(X,Z), ex(Z,X).
:- f(X,Z), rev(X,Z).
:- f(X,Z), ret(X,Z).
:- alt(X,Z), i(X,Z).
:- alt(X,Z), eq(X,Z).
:- alt(X,Z), dis(X,Z).
:- alt(X,Z), ex(X,Z).
:- alt(X,Z), ex(Z,X).
:- alt(X,Z), rev(X,Z).
:- alt(X,Z), ret(X,Z).
:- i(X,Z), eq(X,Z).
:- i(X,Z), dis(X,Z).
:- i(X,Z), ex(X,Z).
:- i(X,Z), ex(Z,X).
:- i(X,Z), rev(X,Z).
:- i(X,Z), ret(X,Z).
:- eq(X,Z), dis(X,Z).
:- eq(X,Z), ex(X,Z).
:- eq(X,Z), ex(Z,X).
:- eq(X,Z), rev(X,Z).
:- eq(X,Z), ret(X,Z).
:- dis(X,Z), ex(X,Z).
:- dis(X,Z), ex(Z,X).
:- dis(X,Z), rev(X,Z).
:- dis(X,Z), ret(X,Z).
:- ex(X,Z), ex(Z,X).
:- ex(X,Z), rev(X,Z).
:- ex(X,Z), ret(X,Z).
:- ex(Z,X), rev(X,Z).
:- ex(Z,X), ret(X,Z).
:- rev(X,Z), ret(X,Z).
