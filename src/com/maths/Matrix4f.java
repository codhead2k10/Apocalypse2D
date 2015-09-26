package com.maths;

public class Matrix4f {

	private float[][] mat;

	public Matrix4f() {
		mat = new float[4][4];
	}

	public Matrix4f loadIdentity() {
		mat[0][0] = 1;mat[0][1] = 0;mat[0][2] = 0;mat[0][3] = 0;
		mat[1][0] = 0;mat[1][1] = 1;mat[1][2] = 0;mat[1][3] = 0;
		mat[2][0] = 0;mat[2][1] = 0;mat[2][2] = 1;mat[2][3] = 0;
		mat[3][0] = 0;mat[3][1] = 0;mat[3][2] = 0;mat[3][3] = 1;
		return this;
	}

	public Matrix4f translation(Vector3f vec) {
		mat[0][0] = 1;mat[0][1] = 0;mat[0][2] = 0;mat[0][3] = vec.x;
		mat[1][0] = 0;mat[1][1] = 1;mat[1][2] = 0;mat[1][3] = vec.y;
		mat[2][0] = 0;mat[2][1] = 0;mat[2][2] = 1;mat[2][3] = vec.z;
		mat[3][0] = 0;mat[3][1] = 0;mat[3][2] = 0;mat[3][3] = 1;
		return this;
	}

	public Matrix4f rotation(Vector3f rotVector) {
		
		
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		float rotX = (float)Math.toRadians(rotVector.x);
		float rotY = (float)Math.toRadians(rotVector.y);
		float rotZ = (float)Math.toRadians(rotVector.z);
		
		rz.mat[0][0] = (float)Math.cos(rotZ);rz.mat[0][1] = -(float)Math.sin(rotZ);rz.mat[0][2] = 0;				rz.mat[0][3] = 0;
		rz.mat[1][0] = (float)Math.sin(rotZ);rz.mat[1][1] = (float)Math.cos(rotZ);rz.mat[1][2] = 0;					rz.mat[1][3] = 0;
		rz.mat[2][0] = 0;					rz.mat[2][1] = 0;					rz.mat[2][2] = 1;					rz.mat[2][3] = 0;
		rz.mat[3][0] = 0;					rz.mat[3][1] = 0;					rz.mat[3][2] = 0;					rz.mat[3][3] = 1;
		
		rx.mat[0][0] = 1;					rx.mat[0][1] = 0;					rx.mat[0][2] = 0;					rx.mat[0][3] = 0;
		rx.mat[1][0] = 0;					rx.mat[1][1] = (float)Math.cos(rotX);rx.mat[1][2] = -(float)Math.sin(rotX);rx.mat[1][3] = 0;
		rx.mat[2][0] = 0;					rx.mat[2][1] = (float)Math.sin(rotX);rx.mat[2][2] = (float)Math.cos(rotX);rx.mat[2][3] = 0;
		rx.mat[3][0] = 0;					rx.mat[3][1] = 0;					rx.mat[3][2] = 0;					rx.mat[3][3] = 1;
		
		ry.mat[0][0] = (float)Math.cos(rotY);ry.mat[0][1] = 0;					ry.mat[0][2] = -(float)Math.sin(rotY);ry.mat[0][3] = 0;
		ry.mat[1][0] = 0;					ry.mat[1][1] = 1;					ry.mat[1][2] = 0;					ry.mat[1][3] = 0;
		ry.mat[2][0] = (float)Math.sin(rotY);ry.mat[2][1] = 0;					ry.mat[2][2] = (float)Math.cos(rotY);ry.mat[2][3] = 0;
		ry.mat[3][0] = 0;					ry.mat[3][1] = 0;					ry.mat[3][2] = 0;					ry.mat[3][3] = 1;
		
		mat = rz.mul(ry.mul(rx)).getMat();
		
		return this;
	}

	public Matrix4f scale(Vector3f vec) {
		mat[0][0] = vec.x;mat[0][1] = 0    ;mat[0][2] = 0    ;mat[0][3] = 0;
		mat[1][0] = 0    ;mat[1][1] = vec.y;mat[1][2] = 0    ;mat[1][3] = 0;
		mat[2][0] = 0    ;mat[2][1] = 0    ;mat[2][2] = vec.z;mat[2][3] = 0;
		mat[3][0] = 0    ;mat[3][1] = 0    ;mat[3][2] = 0    ;mat[3][3] = 1;

		return this;
	}
	
		public Matrix4f projection(float fov, int width, int height, float zNear, float zFar)
		{
			float ar = width/height;
			float tanHalfFOV = (float)Math.tan(Math.toRadians(fov / 2));
			float zRange = zNear - zFar;
			
			mat[0][0] = 1.0f / (tanHalfFOV * ar);	mat[0][1] = 0;					mat[0][2] = 0                    ;	mat[0][3] = 0;
			mat[1][0] = 0;						    mat[1][1] = 1.0f / tanHalfFOV;	mat[1][2] = 0                    ;	mat[1][3] = 0;
			mat[2][0] = 0;						    mat[2][1] = 0;					mat[2][2] = (-zNear -zFar)/zRange;	mat[2][3] = 2 * zFar * zNear / zRange;
			mat[3][0] = 0;						    mat[3][1] = 0;					mat[3][2] = 1                    ;	mat[3][3] = 0;
			
			return this;
		}

	public Matrix4f mul(Matrix4f m) {
		Matrix4f res = new Matrix4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.mat[i][j] = mat[i][0] * m.mat[0][j] 
						      + mat[i][1] * m.mat[1][j] 
						      + mat[i][2] * m.mat[2][j] 
						      + mat[i][3] * m.mat[3][j];
			}
		}

		return res;
	}
	
	public float[][] getMat()
	{
		float[][] res = new float[4][4];
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = mat[i][j];
		
		return res;
	}

	public float get(int i, int j) {
		return mat[i][j];
	}

}