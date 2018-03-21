{s(X,Y); f(X,Y); ex(X,Y); exi(X,Y); alt(X,Y); ret(X,Y); rev(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X<Y.
eq(X,X) :- traj(X).
:- eq(X,Y), eq(Y,Z), not eq(X,Z).
:- eq(X,Y), rev(Y,Z),not rev(X,Z) .
:- eq(X,Y), alt(Y,Z), not alt(X,Z) .
:- eq(X,Y), ret(Y,Z), not ret(X,Z) .
:- eq(X,Y), s(Y,Z), not s(X,Z).
:- eq(X,Y), f(Y,Z), not f(X,Z).
:- eq(X,Y), ex(Y,Z), not ex(X,Z) .
:- eq(X,Y), exi(Y,Z), not exi(X,Z).
:- eq(X,Y), i(Y,Z), not i(X,Z).
:- eq(X,Y), dis(Y,Z), not dis(X,Z).
:- rev(X,Y), eq(Y,Z), not rev(X,Z).
:- rev(X,Y), rev(Y,Z), not eq(X,Z).
:- rev(X,Y), alt(Y,Z), not ret(X,Z).
:- rev(X,Y), ret(Y,Z), not alt(X,Z) .
:- rev(X,Y), s(Y,Z), not exi(X,Z).
:- rev(X,Y), f(Y,Z), not ex(X,Z).
:- rev(X,Y), ex(Y,Z), not f(X,Z).
:- rev(X,Y), exi(Y,Z), not s(X,Z).
:- rev(X,Y), i(Y,Z), not i(X,Z).
:- rev(X,Y), dis(Y,Z), not dis(X,Z).
:- alt(X,Y), eq(Y,Z), not alt(X,Z) .
:- alt(X,Y), rev(Y,Z), not ret(X,Z) .
:- alt(X,Y), alt(Y,Z), not eq(X,Z) , not alt(X,Z)  .
:- alt(X,Y), ret(Y,Z), not rev(X,Z) , not ret(X,Z)  .
:- alt(X,Y), s(Y,Z), not s(X,Z) .
:- alt(X,Y), f(Y,Z), not f(X,Z) .
:- alt(X,Y), ex(Y,Z), not ex(X,Z) .
:- alt(X,Y), exi(Y,Z), not exi(X,Z) .
:- alt(X,Y), i(Y,Z), not i(X,Z) , not dis(X,Z) .
:- alt(X,Y), dis(Y,Z), not i(X,Z) , not dis(X,Z) .
:- ret(X,Y), eq(Y,Z), not ret(X,Z) .
:- ret(X,Y), rev(Y,Z), not alt(X,Z) .
:- ret(X,Y), alt(Y,Z), not rev(X,Z) , not ret(X,Z)  .
:- ret(X,Y), ret(Y,Z), not eq(X,Z) , not alt(X,Z)  .
:- ret(X,Y), s(Y,Z), not exi(X,Z) .
:- ret(X,Y), f(Y,Z), not ex(X,Z) .
:- ret(X,Y), ex(Y,Z), not f(X,Z) .
:- ret(X,Y), exi(Y,Z), not s(X,Z) .
:- ret(X,Y), i(Y,Z), not i(X,Z) , not dis(X,Z) .
:- ret(X,Y), dis(Y,Z), not i(X,Z) , not dis(X,Z) .
:- s(X,Y), eq(Y,Z), not s(X,Z) .
:- s(X,Y), rev(Y,Z), not ex(X,Z) .
:- s(X,Y), alt(Y,Z), not s(X,Z) .
:- s(X,Y), ret(Y,Z), not ex(X,Z) .
:- s(X,Y), s(Y,Z), not eq(X,Z) , not alt(X,Z) , not s(X,Z) .
:- s(X,Y), f(Y,Z), not exi(X,Z) , not i(X,Z) , not dis(X,Z) .
:- s(X,Y), ex(Y,Z), not rev(X,Z) , not ret(X,Z) , not ex(X,Z).
:- s(X,Y), exi(Y,Z), not f(X,Z) , not i(X,Z) , not dis(X,Z) .
:- s(X,Y), i(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .
:- s(X,Y), dis(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .
:- f(X,Y), eq(Y,Z), not f(X,Z) .
:- f(X,Y), rev(Y,Z), not exi(X,Z) .
:- f(X,Y), alt(Y,Z), not f(X,Z) .
:- f(X,Y), ret(Y,Z), not exi(X,Z) .
:- f(X,Y), s(Y,Z), not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- f(X,Y), f(Y,Z), not eq(X,Z) , not alt(X,Z) , not f(X,Z) .
:- f(X,Y), ex(Y,Z), not s(X,Z) , not i(X,Z) , not dis(X,Z).
:- f(X,Y), exi(Y,Z), not rev(X,Z) , not ret(X,Z) , not exi(X,Z) .
:- f(X,Y), i(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- f(X,Y), dis(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- ex(X,Y), eq(Y,Z), not ex(X,Z).
:- ex(X,Y), rev(Y,Z), not s(X,Z) .
:- ex(X,Y), alt(Y,Z), not ex(X,Z) .
:- ex(X,Y), ret(Y,Z), not s(X,Z) .
:- ex(X,Y), s(Y,Z), not f(X,Z) , not i(X,Z) , not dis(X,Z) .
:- ex(X,Y), f(Y,Z), not rev(X,Z) , not ret(X,Z) , not ex(X,Z) .
:- ex(X,Y), ex(Y,Z), not exi(X,Z) , not i(X,Z) , not dis(X,Z).
:- ex(X,Y), exi(Y,Z), not eq(X,Z) , not alt(X,Z) , not s(X,Z) .
:- ex(X,Y), i(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .
:- ex(X,Y), dis(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .
:- exi(X,Y), eq(Y,Z), not exi(X,Z) .
:- exi(X,Y), rev(Y,Z), not f(X,Z) .
:- exi(X,Y), alt(Y,Z), not exi(X,Z) .
:- exi(X,Y), ret(Y,Z), not f(X,Z) .
:- exi(X,Y), s(Y,Z), not rev(X,Z) , not ret(X,Z) , not exi(X,Z) .
:- exi(X,Y), f(Y,Z), not s(X,Z) , not i(X,Z) , not dis(X,Z) .
:- exi(X,Y), ex(Y,Z), not eq(X,Z) , not alt(X,Z) , not f(X,Z).
:- exi(X,Y), exi(Y,Z), not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- exi(X,Y), i(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- exi(X,Y), dis(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- i(X,Y), eq(Y,Z), not i(X,Z) .
:- i(X,Y), rev(Y,Z), not i(X,Z) .
:- i(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z) .
:- i(X,Y), ret(Y,Z), not i(X,Z), not dis(X,Z) .
:- i(X,Y), s(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- i(X,Y), f(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).
:- i(X,Y), ex(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).
:- i(X,Y), exi(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z).
:- i(X,Y), dis(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z) , not f(X,Z), not ex(X,Z) , not exi(X,Z), not i(X,Z) , not dis(X,Z)  .
:- dis(X,Y), eq(Y,Z), not dis(X,Z).
:- dis(X,Y), rev(Y,Z), not dis(X,Z).
:- dis(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z) .
:- dis(X,Y), ret(Y,Z), not i(X,Z), not dis(X,Z) .
:- dis(X,Y), s(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .
:- dis(X,Y), f(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).
:- dis(X,Y), ex(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).
:- dis(X,Y), exi(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z).
:- dis(X,Y), i(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z) , not f(X,Z), not ex(X,Z) , not exi(X,Z), not i(X,Z) , not dis(X,Z)  .
exi(X,Y) :- ex(Y,X), Y<X.
ex(X,Y) :- exi(Y,X), Y<X.
