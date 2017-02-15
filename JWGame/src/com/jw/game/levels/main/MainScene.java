package com.jw.game.levels.main;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jw.game.levels.main.protagonist.ProtagonistAnimationControl;
import com.jw.game.levels.main.protagonist.ProtagonistControl;
import com.jw.game.levels.main.protagonist.ProtagonistState;

public class MainScene extends AbstractAppState {

    private SimpleApplication app;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private BulletAppState bulletAppState;
    private Node rootNode;
    private Camera camera;
    private CameraNode cameraNode;
    private Spatial sky;
    private Spatial scene;
    private Spatial protagonist;
    private Node protagonistNode;
    private ProtagonistControl protagonistControl;
    private ProtagonistAnimationControl protagonistAnimationControl;
    private ProtagonistState protagonistState;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.stateManager = this.app.getStateManager();
        this.assetManager = this.app.getAssetManager();
        this.camera = this.app.getCamera();
        this.rootNode = this.app.getRootNode();

        addBulletAppState();
        addScene();
        addSky();
        addProtagonist();
        addCamera();

        Spatial house = assetManager.loadModel("Models/houses/home/home.j3o");
        house.setName("home");
        house.setLocalTranslation(10f, 0f, 10f);
        CollisionShape houseShape = CollisionShapeFactory.createMeshShape(house);
        RigidBodyControl houseRigidBodyControl = new RigidBodyControl(houseShape, 0);
        house.addControl(houseRigidBodyControl);
        getPhysicsSpace().add(houseRigidBodyControl);
        rootNode.attachChild(house);
    }

    private void addScene() {
        scene = assetManager.loadModel(MainData.mainSceneAsset());
        scene.setName(MainData.mainSceneSpartial());
        this.rootNode.attachChild(scene);
        this.bulletAppState.getPhysicsSpace().addAll(scene);
    }

    private void addSky() {
        Texture west = assetManager.loadTexture(MainData.mainSkyTextureWest());
        Texture east = assetManager.loadTexture(MainData.mainSkyTextureEast());
        Texture north = assetManager.loadTexture(MainData.mainSkyTextureNorth());
        Texture south = assetManager.loadTexture(MainData.mainSkyTextureSouth());
        Texture up = assetManager.loadTexture(MainData.mainSkyTextureUp());
        Texture down = assetManager.loadTexture(MainData.mainSkyTextureDown());

        sky = SkyFactory.createSky(assetManager, west, east, north, south, up, down);
        sky.setName(MainData.mainSkySpartial());
        this.rootNode.attachChild(sky);
    }

    public void addProtagonist() {
        protagonistNode = new Node(MainData.nameProtagonistNode());
        protagonist = assetManager.loadModel(MainData.mainProtagonistAsset());
        protagonist.setName(MainData.nameProtagonistSpartial());
        protagonistNode.attachChild(protagonist);
        rootNode.attachChild(protagonistNode);

        protagonistControl = new ProtagonistControl(0.25f, 2.0f, 45f);
        protagonistNode.addControl(protagonistControl);
        getPhysicsSpace().add(protagonistControl);

        protagonistAnimationControl = new ProtagonistAnimationControl();
        protagonistNode.addControl(protagonistAnimationControl);
        protagonistState = new ProtagonistState();
        stateManager.attach(protagonistState);
    }

    public void addCamera() {
        cameraNode = new CameraNode(MainData.nameCameraNode(), camera);
        cameraNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        cameraNode.setLocalTranslation(new Vector3f(0, 2, -6));
        Quaternion quat = new Quaternion();
        quat.lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);
        cameraNode.setLocalRotation(quat);
        protagonistNode.attachChild(cameraNode);
        cameraNode.setEnabled(true);
    }

    private void addBulletAppState() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.setDebugEnabled(true);
    }

    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        this.rootNode.detachAllChildren();
        stateManager.detach(bulletAppState);
        stateManager.detach(protagonistState);
    }
}
