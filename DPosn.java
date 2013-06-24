

/**A Posn with two doubles instead of integers, 
 * better used for Ship de-acceleration*/
public class DPosn{
      double x;
      double y;
      DPosn(double x, double y){
          this.x = x;
          this.y = y;
      }
      
      void dposnXUp(){
          this.x = this.x + 1;
      }
      
      void dposnYUp(){
          this.y = this.y + 1;
      }
      
      void dposnXDown(){
          this.x = this.x - 1;
      }
      
      void dposnYDown(){
          this.y = this.y - 1;
      }
      
      /** Is this object "equal" to the given object? */
      public boolean equals(Object o){
          if(o instanceof DPosn){
              DPosn p1 = this;
              DPosn p2 = (DPosn) o;
              return p1.x == p2.x && p1.y == p2.y;
          }else{ 
              return false;
              }
      }
      
}
