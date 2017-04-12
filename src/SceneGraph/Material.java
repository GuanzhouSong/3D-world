package SceneGraph;

import java.awt.Color;
import java.util.Arrays;
import javax.media.opengl.GL;

public class Material {
	protected Color ambient;
	protected Color diffuse;
	protected Color specular;
	protected Color emission;
	protected float shininess = 1.0f;

	public static final Material CLAY = new Material(Color.GRAY, Color.GRAY, Color.BLACK, 0.0f);
	public static final Material BRONZE = new Material(
			new Color(0.2125f, 0.1275f, 0.0545f),
			new Color(0.7145f, 0.4284f, 0.1814f),
			new Color(0.3936f, 0.2719f, 0.1667f), 51.2f);
	public static final Material WOOD = new Material(
			new Color(0.3961f, 0.2667f, 0.0988f),
			new Color(0.3961f, 0.2667f, 0.0988f),
			new Color(0.0125f, 0.0010f, 0.0010f), 1.12f);
	public static final Material SAND = new Material(
			new Color(0.8061f, 0.7667f, 0.3121f),
			new Color(0.8061f, 0.7667f, 0.3121f),
			new Color(0.0125f, 0.0101f, 0.0000f), 0.77f);
	public static final Material GREEN_PLASTIC = new Material(
			new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.1250f, 0.3500f, 0.1250f),
			new Color(0.0450f, 0.0550f, 0.0450f), 0.25f);
	public static final Material BLACK = new Material(
			new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.0000f, 0.0000f, 0.0000f), 0.25f);
	public static final Material Jade = new Material(//ÂÌÉ«
			new Color(0.1350f, 0.2225f, 0.1575f),
			new Color(0.5400f, 0.8900f, 0.6300f),
			new Color(0.3162f, 0.3162f, 0.3162f), 12.8f);
	public static final Material Emerald = new Material(//´äÂÌ
			new Color(0.0215f, 0.1745f, 0.0215f),
			new Color(0.0757f, 0.6142f, 0.0757f),
			new Color(0.6330f, 0.7278f, 0.6330f), 77.0f);
	public static final Material Obsidian = new Material(//ºÚê×Ê¯			
			new Color(0.0538f, 0.0500f, 0.0662f),
			new Color(0.1828f, 0.1700f, 0.2252f),
			new Color(0.3327f, 0.3286f, 0.3464f), 39.0f);
	public static final Material Pearl = new Material(//ÕäÖé°×
			new Color(0.0005f, 0.0000f, 0.0000f),
			new Color(1.0000f, 0.8290f, 0.8290f),
			new Color(0.2966f, 0.2966f, 0.2966f), 11.0f);
	public static final Material Ruby = new Material(//ºì±¦Ê¯
			new Color(0.1745f, 0.0118f, 0.0118f),
			new Color(0.6142f, 0.0414f, 0.0414f),
			new Color(0.7278f, 0.6270f, 0.6270f), 77.0f);
	public static final Material Turquoise = new Material(
			new Color(0.1000f, 0.1873f, 0.1745f),
			new Color(0.3960f, 0.7415f, 0.6910f),
			new Color(0.2973f, 0.3083f, 0.3067f), 12.8f);
	public static final Material Brass = new Material(//»ÆÍ­
			new Color(0.3294f, 0.2235f, 0.0275f),
			new Color(0.7804f, 0.5686f, 0.1137f),
			new Color(0.9921f, 0.9411f, 0.8078f), 30.0f);
	public static final Material Bronze = new Material(//ÇàÍ­
			new Color(0.2125f, 0.1275f, 0.0540f),
			new Color(0.7140f, 0.4284f, 0.1814f),
			new Color(0.3935f, 0.2719f, 0.1667f), 25.6f);
	public static final Material Chrome = new Material(//¸õ
			new Color(0.2500f, 0.2500f, 0.2500f),
			new Color(0.4000f, 0.4000f, 0.4000f),
            new Color(0.7746f, 0.7746f, 0.7745f), 76.8f);
	public static final Material Copper = new Material(//Í­
			new Color(0.1913f, 0.0735f, 0.0225f),
			new Color(0.7038f, 0.2705f, 0.0828f),
			new Color(0.2568f, 0.1376f, 0.0860f), 12.8f);
	public static final Material Gold = new Material(//½ðÉ«
			new Color(0.2473f, 0.1995f, 0.0745f),
			new Color(0.7516f, 0.6065f, 0.2265f),
		    new Color(0.6283f, 0.5558f, 0.3661f), 51.2f);
	public static final Material Silver = new Material(//ÒøÉ«
			new Color(0.1923f, 0.1923f, 0.1923f),
			new Color(0.5075f, 0.5075f, 0.5075f),
			new Color(0.5083f, 0.5083f, 0.5083f), 51.2f);
	public static final Material BlackPlastic = new Material(//ºÚËÜÁÏ
			new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.0100f, 0.0100f, 0.0100f),
			new Color(0.5000f, 0.5000f, 0.5000f), 32.0f);
	public static final Material CyanPlastic = new Material(//ÇàËÜÁÏ
			new Color(0.0000f, 0.1000f, 0.0600f),
			new Color(0.0000f, 0.5098f, 0.5098f),
			new Color(0.5020f, 0.5020f, 0.5020f), 32.0f);
	public static final Material GreenPlastic = new Material(//ÂÌËÜÁÏ
			new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.1000f, 0.3500f, 0.1000f),
			new Color(0.4500f, 0.5500f, 0.4500f), 32.0f);
    public static final Material RedPlastic = new Material(//ºìËÜÁÏ
    		new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.5000f, 0.0000f, 0.0000f),
			new Color(0.7000f, 0.6000f, 0.6000f), 32.0f);
    public static final Material WhitePlastic = new Material(//°×ËÜÁÏ
    		new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.5500f, 0.5500f, 0.5500f),
			new Color(0.7000f, 0.7000f, 0.7000f), 32.0f);
    public static final Material YellowPlastic = new Material(//»ÆËÜÁÏ
    		new Color(0.0000f, 0.0000f, 0.0000f),
			new Color(0.5000f, 0.5000f, 0.0000f),
			new Color(0.6000f, 0.6000f, 0.5000f), 32.0f);
    public static final Material BlackRubber = new Material(//Ïð½ººÚ
    		new Color(0.0200f, 0.0200f, 0.0200f),
			new Color(0.0100f, 0.0100f, 0.0100f),
			new Color(0.4000f, 0.4000f, 0.4000f), 10.0f);
    public static final Material CyanRubber = new Material(//Ïð½ºÇà
     		new Color(0.0000f, 0.0500f, 0.0500f),
    		new Color(0.4000f, 0.5000f, 0.5000f),
    		new Color(0.0400f, 0.7000f, 0.7000f), 10.0f);
    public static final Material GreenRubber = new Material(//Ïð½ºÂÌ
    		new Color(0.0000f, 0.0500f, 0.0000f),
    		new Color(0.4000f, 0.5000f, 0.4000f),
    		new Color(0.0400f, 0.7000f, 0.0400f), 10.0f);
    public static final Material RedRubber = new Material(//Ïð½ººì
    		new Color(0.0500f, 0.0000f, 0.0000f),
    		new Color(0.5000f, 0.4000f, 0.4000f),
    		new Color(0.7000f, 0.0400f, 0.0400f), 10.0f);
    public static final Material WhiteRubber = new Material(//Ïð½º°×
    		new Color(0.0500f, 0.0500f, 0.0500f),
    		new Color(0.5000f, 0.5000f, 0.5000f),
    		new Color(0.7000f, 0.7000f, 0.7000f), 10.0f);
    public static final Material YellowRubber = new Material(//Ïð½º»Æ
    		new Color(0.0500f, 0.0500f, 0.0000f),
    		new Color(0.5000f, 0.5000f, 0.4000f),
    		new Color(0.7000f, 0.7000f, 0.0400f), 10.0f);
    public static final Material NeutralWhite = new Material(//ÖÐÐÔ°×
    		new Color(1.0000f, 1.0000f, 1.0000f),
    		new Color(1.0000f, 1.0000f, 1.0000f),
    		new Color(0.0000f, 0.0000f, 0.0000f), 0.0f);
    
    public Material(
			Color ambient, Color diffuse, Color specular,
			Color emission, float shininess) {
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.emission = emission;
		this.shininess = shininess;
	}
	
	public Material(Color ambient, Color diffuse, Color specular, float shininess) {
		this(ambient, diffuse, specular, Color.BLACK, shininess);
	}
	
	public void apply(GL gl, int side) {
		gl.glMaterialfv(side, GL.GL_AMBIENT,   ambient.getComponents(null), 0);
        gl.glMaterialfv(side, GL.GL_DIFFUSE,   diffuse.getComponents(null), 0);
        gl.glMaterialfv(side, GL.GL_SPECULAR, specular.getComponents(null), 0);
        gl.glMaterialfv(side, GL.GL_EMISSION, emission.getComponents(null), 0);
        
        gl.glMaterialf(side, GL.GL_SHININESS, shininess);
	}
	
	public void apply(GL gl) {
		apply(gl, GL.GL_FRONT);
	}
}

