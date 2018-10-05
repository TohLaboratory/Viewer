foreach i (*.jar)
  jar tf $i | grep Prologue
end
