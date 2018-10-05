# mtx <- matrix(c(0.0,0.6,1.4,1.3,1.0,0.6,0.0,1.8,1.7,1.4,1.4,1.8,0.0,0.5,0.6,1.3,1.7,0.5,0.0,0.5,1.0,1.4,0.6,0.5,0.0), ncol=5, byrow=T)
mtx <- matrix(c(0.0,3.3,4.3,6.8,5.4,3.3,0.0,2.0,6.1,4.7,4.3,2.0,0.0,7.1,5.7,6.8,6.1,7.1,0.0,3.6,5.4,4.7,5.7,3.6,0.0), ncol=5, byrow=T)
minS <- 10000
for (i in 1:(ncol(mtx)-1))  {
	for (j in (i + 1):ncol(mtx))  {
		s <- 0.5*mtx[i,j]
		for (k in 1:ncol(mtx))  {
			if (k == i || k == j)  next
			else    {
			    s <- s + mtx[i, k]/2/(ncol(mtx)-2)
			    s <- s + mtx[j, k]/2/(ncol(mtx)-2)
			}
		}
		for (k in 1:(ncol(mtx)-1))  {
			if ( k == i || k == j )  next
			for (l in (k + 1):ncol(mtx))  {
				if (l == i || l == j)  next
				else    s <- s + mtx[k, l]/(ncol(mtx)-2)
			}
		}
		print(c(">> ", i, j, s))
	    if (minS > s)  {
	    	minS <- s
	    	minPair1 <- i
	    	minPair2 <- j
	    	print(c(minPair1,minPair2))
	    	print(minS)
	    	bl1 <- 0
	    	bl2 <- 0
	    	for (k in 1:ncol(mtx)) {
	    		if (k == i || k == j)  next
	    		else  {
	    		    bl1 <- bl1 + mtx[minPair1, k]/(ncol(mtx)-2)
	    		    bl2 <- bl2 + mtx[minPair2, k]/(ncol(mtx)-2)	
	    		}
	    	}
	    	x1 <- (mtx[minPair1, minPair2]+bl1 - bl2)/2
	    	x2 <- (mtx[minPair1, minPair2]+bl2 - bl1)/2
	    	print(c("x1 = ",x1," x2 = ",x2))
	    }	
	}
}