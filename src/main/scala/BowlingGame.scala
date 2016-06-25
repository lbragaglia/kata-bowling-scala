import scala.annotation.tailrec


class BowlingGame {

  def score(input: List[Int]): Int = {

            // [alternativa per gestire i casi speciali: aggiungere sempre due tiri finali da 0]
    
            def calculator(frames: Int, result: Int, input: List[Int]): Int = {
                input match {
                  case Nil => result
                  
                  // casi speciali finali
                  case uno::due::tre::Nil if(frames == 10 && uno==10) => result+(uno+due+tre)                  
                  case uno::due::tre::Nil if(frames == 10 && uno+due==10) => result+(uno+due+tre)
                  
                  // strike
                  case uno::due::tre::xs if(uno==10) => calculator(frames+1, result+(uno+due+tre), due::tre::xs)
                  // spare
                  case uno::due::tre::xs if(uno+due==10) => calculator(frames+1, result+(uno+due+tre), tre::xs)
                  
                  // frame normale
                  case uno::due::tre::xs => calculator(frames+1, result+(uno+due), tre::xs)
                  case x::xs => calculator(frames, result+x, xs)
                } 
            }
            
            calculator(1, 0, input)
    }
  
  def score(input: String): Int = {
    @tailrec
    def converter(result: List[Int], input: List[Char]): List[Int] = {
      input match {
        case Nil => result
        case '-'::xs => converter(result:+0, xs)
        case 'X'::xs => converter(result:+10, xs)
        case x::'/'::xs => converter(result:+(x.getNumericValue):+(10-x.getNumericValue), xs)
        case x::xs => converter(result:+x.getNumericValue, xs)
      }
    }
    
    score(converter(List(), input.toList))
  }
}